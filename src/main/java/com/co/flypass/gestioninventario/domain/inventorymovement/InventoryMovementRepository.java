package com.co.flypass.gestioninventario.domain.inventorymovement;

import com.co.flypass.gestioninventario.domain.product.Product;

import java.util.List;

public interface InventoryMovementRepository {


    void save(InventoryMovement inventoryMovement);

    List<InventoryMovement> findAllMovements();
}
