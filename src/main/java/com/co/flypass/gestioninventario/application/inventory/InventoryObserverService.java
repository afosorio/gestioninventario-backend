package com.co.flypass.gestioninventario.application.inventory;

import com.co.flypass.gestioninventario.application.product.ProductService;
import com.co.flypass.gestioninventario.domain.product.ProductEvent;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
class InventoryObserverService {

    private static final Logger log = LoggerFactory.getLogger(InventoryObserverService.class);
    private final ProductService productService;
    private final InventoryMovementService inventoryMovementService;
    private final ExecutorService executorService;

    public InventoryObserverService(ProductService productService, InventoryMovementService inventoryMovementService, ExecutorService executorService) {
        this.productService = productService;
        this.inventoryMovementService = inventoryMovementService;
        this.executorService = executorService;
    }

    @PostConstruct
    public void init() {
        productService.getInventoryEvents().observeOn(Schedulers.from(executorService)).subscribe(this::procesaRecibido);
    }

    private void procesaRecibido(ProductEvent event) {

        log.info("Evento Recibido : {} - Producto: {}", event.getTypeEvent(),event.getProduct().getName());

        inventoryMovementService.createMovement(event.getProduct(), event.getTypeEvent(), event.getQuantity());
    }
}
