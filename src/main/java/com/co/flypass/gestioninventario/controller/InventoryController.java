package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.inventory.InventoryMovementService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryMovementService inventoryMovementService;

    public InventoryController(InventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    @GetMapping
    public Response<Object> getAllMovements()   {
        return new Response<>(HttpServletResponse.SC_OK, "Producto Eliminado");
       // return inventoryMovementService.getAllMovements();
    }
}
