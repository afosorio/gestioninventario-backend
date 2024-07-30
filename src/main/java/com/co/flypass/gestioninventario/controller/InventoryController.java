package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.inventory.InventoryMovementService;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryMovementService inventoryMovementService;

    public InventoryController(InventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    @GetMapping
    public Response<List<InventoryMovement>> getAllMovements()   {
        return new Response<>(HttpServletResponse.SC_OK, "Lista de Movimientos Inventario", inventoryMovementService.getAll());
    }
}