package com.co.flypass.gestioninventario.application.product;

import com.co.flypass.gestioninventario.application.category.CategoryService;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEvent;
import com.co.flypass.gestioninventario.domain.product.ProductEventType;
import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import com.co.flypass.gestioninventario.exception.AppException;
import com.co.flypass.gestioninventario.exception.DataBaseException;
import com.co.flypass.gestioninventario.exception.NoDataFoundException;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import jakarta.persistence.OptimisticLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final PublishSubject<ProductEvent> inventoryEvents = PublishSubject.create();
    private final ReentrantLock lock = new ReentrantLock();
    private final Executor threadPool;

    public Observable<ProductEvent> getInventoryEvents() {
        return inventoryEvents;
    }

    public ProductService(ProductRepository productRepository, CategoryService categoryService, Executor threadPool) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.threadPool = threadPool;
    }

    @Transactional
    public void createProduct(Product product) {

        categoryService.existCategory(product.getCategory().getId());
        Product saveProduct = productRepository.save(product);
        inventoryEvents.onNext(new ProductEvent(ProductEventType.ENTRY, saveProduct, product.getStockQuantity()));
    }

    @Transactional
    public void deleteProduct(long productId) {

        Product product = productRepository.findProductById(productId).orElseThrow(() -> new AppException("Producto no encontrado"));
        if (isProductOutOfStock(product)) {
            CompletableFuture.runAsync(() -> productRepository.delete(productId), threadPool);
        } else {
            throw new AppException("El producto aún tiene stock");
        }
    }

    private boolean isProductOutOfStock(Product product) {
        return product.getStockQuantity() == 0;
    }

    public CompletableFuture<Void> updatePriceAndQuantity(long productId, double price, int quantity) {

        Product product = getProductById(productId);

        int originalQuantity = getProductById(productId).getStockQuantity();
        product.setStockQuantity(quantity);
        product.setPrice(price);

        ProductEventType eventType = quantity < originalQuantity ? ProductEventType.EXIT : ProductEventType.ENTRY;
        return update(product, eventType, quantity);
    }

    public Product getProductById(long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new NoDataFoundException("Producto no encontrado"));
    }

    public CompletableFuture<Void> addStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() + quantity);
        return update(product, ProductEventType.ENTRY, quantity);
    }

    public CompletableFuture<Void> removeStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() - quantity);
        return update(product, ProductEventType.EXIT, quantity);
    }

    @Transactional
    private CompletableFuture<Void> update(Product product, ProductEventType type, int quantity) {

        return CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                productRepository.update(product);
                inventoryEvents.onNext(new ProductEvent(type, product, quantity));

            }catch (OptimisticLockException exception) {
                throw new AppException("Ocurrió un error de concurrencia actualizando el producto", exception);
            }catch (Exception e){
                throw new AppException("Ocurrió un error actualizando el producto", e);
            }finally {
                lock.unlock();
            }
        }, threadPool).exceptionally(ex -> {
            throw new AppException("Ocurrió un error actualizando el producto", ex);
        });
    }

    public CompletableFuture<List<Product>> getAllProducts() {
        return CompletableFuture.supplyAsync(productRepository::findAllProducts, threadPool);
    }

    public List<Product> getProducts(int category, LocalDate startDate, LocalDate endDate) {
        Optional<List<Product>> optionalList = productRepository.findProducts(category, startDate, endDate);
        optionalList.orElseThrow(() -> new NoDataFoundException("No se encontraron productos"));
        return optionalList.get();
    }
}