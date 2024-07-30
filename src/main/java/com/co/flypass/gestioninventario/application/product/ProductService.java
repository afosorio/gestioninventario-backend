package com.co.flypass.gestioninventario.application.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEvent;
import com.co.flypass.gestioninventario.domain.product.ProductEventType;
import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import com.co.flypass.gestioninventario.exception.AppException;
import com.co.flypass.gestioninventario.exception.NoDataFoundException;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final PublishSubject<ProductEvent> inventoryEvents = PublishSubject.create();
    private final ReentrantLock lock = new ReentrantLock();
    private final ExecutorService threadPool;

    public Observable<ProductEvent> getInventoryEvents() {
        return inventoryEvents;
    }

    public ProductService(ProductRepository productRepository, ExecutorService threadPool) {
        this.productRepository = productRepository;
        this.threadPool = threadPool;
    }

    @Transactional
    public void createProduct(Product product) {

        CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                Product saveProduct = productRepository.save(product);
                inventoryEvents.onNext(new ProductEvent(ProductEventType.ENTRY, saveProduct));
            } catch (Exception e) {
                throw new RuntimeException("Error ", e);
            } finally {
                lock.unlock();
            }
        }, threadPool);
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

    public void updatePriceAndQuantity(long productId, double price, int quantity) {

        Product product = getProductById(productId);

        int originalQuantity = getProductById(productId).getStockQuantity();
        product.setStockQuantity(quantity);
        product.setPrice(price);

        ProductEventType eventType = quantity < originalQuantity ? ProductEventType.EXIT : ProductEventType.ENTRY;
        update(product, eventType);
    }

    public Product getProductById(long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new NoDataFoundException("Producto no encontrado"));
    }

    public void addStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() + quantity);
        update(product, ProductEventType.ENTRY);
    }

    public void removeStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() - quantity);
        update(product, ProductEventType.EXIT);
    }

    @Transactional
    private void update(Product product, ProductEventType type) {

        CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                productRepository.update(product);
                inventoryEvents.onNext(new ProductEvent(type, product));
            } finally {
                lock.unlock();
            }
        }, threadPool).exceptionally(ex -> {
            throw new AppException("Ocurrió un error actualizando el producto", ex);
        });
    }

    public CompletableFuture<List<Product>> getAllProducts() {
        return CompletableFuture.supplyAsync(productRepository::findAllProducts, threadPool);
    }

    public List<Product> getProducts(Category category, LocalDate startDate, LocalDate endDate) {
        Optional<List<Product>> optionalList = productRepository.findProducts(category, startDate, endDate);
        optionalList.orElseThrow(() -> new NoDataFoundException("No se encontraron productos"));
        return optionalList.get();
    }
}