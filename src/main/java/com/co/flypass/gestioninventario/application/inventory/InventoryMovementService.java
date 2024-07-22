package com.co.flypass.gestioninventario.application.inventory;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovementRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovementService(InventoryMovementRepository inventoryMovementRepository) {
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    public InventoryMovement createMovement(InventoryMovement inventoryMovement) {
        return new InventoryMovement();
    }


    public List<InventoryMovement> getAll(){
        return Collections.EMPTY_LIST;
    }
}
