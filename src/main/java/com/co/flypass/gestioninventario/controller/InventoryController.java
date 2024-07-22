package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.inventory.InventoryMovementService;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryMovementService inventoryMovementService;

    public InventoryController(InventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    @GetMapping
    public List<InventoryMovement> getAllMovements()   {
        return Collections.EMPTY_LIST;
       // return inventoryMovementService.getAllMovements();
    }

    @PostMapping
    public InventoryMovement createInventoryMovement(@Valid @RequestBody InventoryMovement reservation) {
        return inventoryMovementService.createMovement(reservation);
    }

    @PutMapping
    public ResponseEntity<InventoryMovement> updateInventory(@Valid @RequestBody InventoryMovement inventoryMovement) {
       // InventoryMovement updatedInventoryMovement = inventoryMovementService.updateMovement(inventoryMovement);
        return ResponseEntity.ok(null);
    }

}
