package com.co.flypass.gestioninventario.application.inventory;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovementRepository;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEventType;
import com.co.flypass.gestioninventario.exception.AppException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;
    private final ExecutorService threadPool;
    private final ReentrantLock lock = new ReentrantLock();

    public InventoryMovementService(InventoryMovementRepository inventoryMovementRepository, ExecutorService threadPool) {
        this.inventoryMovementRepository = inventoryMovementRepository;
        this.threadPool = threadPool;
    }

    @Transactional
    public void createMovement(Product product, ProductEventType eventType) {

        CompletableFuture.runAsync(() -> {
            lock.lock();
            try {
                InventoryMovement inventoryMovement = new InventoryMovement();
                inventoryMovement.setDate(LocalDate.now());
                inventoryMovement.setType(eventType);
                inventoryMovement.setProduct(product);
                inventoryMovement.setQuantity(product.getStockQuantity());
                inventoryMovementRepository.save(inventoryMovement);
            } finally {
                lock.unlock();
            }
        }, threadPool).exceptionally(ex -> {
            throw new AppException("Ocurrió un error actualizando el producto", ex);
        });
    }

    public List<InventoryMovement> getAll(){
        return  inventoryMovementRepository.findAllMovements();
    }
}
