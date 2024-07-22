package com.co.flypass.gestioninventario.application.product;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEvent;
import com.co.flypass.gestioninventario.domain.product.ProductEventType;
import com.co.flypass.gestioninventario.domain.product.ProductRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

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
                productRepository.save(product);
                inventoryEvents.onNext(new ProductEvent());
            } finally {
                lock.unlock();
            }
        }, threadPool);
    }

    @Transactional
    public void deleteProduct(long productId) {

        Optional<Product> productOptional = productRepository.findProductById(productId);
        if (productOptional.isPresent() && isProductOutOfStock(productOptional.get())) {
            CompletableFuture.runAsync(() -> productRepository.delete(productId), threadPool);
        }
    }

    private boolean isProductOutOfStock(Product product) {
        return product.getStockQuantity() == 0;
    }

    public void updatePriceAndQuantity(long productId, double price, int quantity) {

        Optional<Product> productOptional = productRepository.findProductById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStockQuantity(quantity);
            product.setPrice(price);
            update(product, ProductEventType.ADD);
        }
    }

    public void addStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() + quantity);
        update(product, ProductEventType.ADD);
    }

    public void removeStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() - quantity);
        update(product, ProductEventType.REMOVE);
    }

    @Transactional
    private void update(Product product, ProductEventType type ) {

        CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                productRepository.update(product);
                inventoryEvents.onNext(new ProductEvent());
            } finally {
                lock.unlock();
            }
        }, threadPool).exceptionally(exception -> {
            log.error("Error al enviar evento a inventory");
            return null;
        });
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }
}