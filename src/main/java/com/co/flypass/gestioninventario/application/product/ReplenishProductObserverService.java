package com.co.flypass.gestioninventario.application.product;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEvent;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
class ReplenishProductObserverService {

    private static final Logger log = LoggerFactory.getLogger(ReplenishProductObserverService.class);
    private final ProductService productService;
    private final Executor executorService;
    private static final int  MIN_UMBRAL_QUANTITY = 5;

    ReplenishProductObserverService(ProductService productService, Executor executorService) {
        this.productService = productService;
        this.executorService = executorService;
    }

    @PostConstruct
    public void init() {
        productService.getInventoryEvents().observeOn(Schedulers.from(executorService)).subscribe(this::replanishInventory);
    }

    private void replanishInventory(ProductEvent event) {

        if(isLowStock(event.getProduct())){
            productService.addStock(event.getProduct(), 100);
        }
    }

    private boolean isLowStock(Product product){
        return product.getStockQuantity() < MIN_UMBRAL_QUANTITY;
    }
}
