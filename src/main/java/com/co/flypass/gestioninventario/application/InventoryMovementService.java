package com.co.flypass.gestioninventario.application;

import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovementRepository;
import com.co.flypass.gestioninventario.domain.product.Product;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovementService(InventoryMovementRepository inventoryMovementRepository) {
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    public List<InventoryMovement> getAllMovements() {
        return Collections.singletonList(new InventoryMovement());
    }

    public InventoryMovement createMovement(InventoryMovement inventoryMovement) {
        return new InventoryMovement();
    }

    public InventoryMovement updateMovement(InventoryMovement inventoryMovement) {
        return new InventoryMovement();
    }

}
