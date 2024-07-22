package com.co.flypass.gestioninventario.application.product;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEvent;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.annotation.PostConstruct;

import java.util.concurrent.ExecutorService;

class ReplenishProductObserverService {

    private final ProductService productService;
    private final ExecutorService executorService;
    private static final int  MIN_UMBRAL_QUANTITY = 5;

    ReplenishProductObserverService(ProductService productService, ExecutorService executorService) {
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
