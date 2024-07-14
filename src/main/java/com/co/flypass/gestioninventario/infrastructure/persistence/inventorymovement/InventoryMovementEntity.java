package com.co.flypass.gestioninventario.infrastructure.persistence.inventorymovement;

import com.co.flypass.gestioninventario.infrastructure.persistence.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementEntity {

    private long id;
    private ProductEntity productEntity;
    private String type; // entry or exit
    private int quantity;
    private LocalDate date;

}
