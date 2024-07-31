package com.co.flypass.gestioninventario.domain.inventorymovement;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.product.ProductEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovement {

    private long id;
    private Product product;
    private ProductEventType type;
    private int quantity;
    private LocalDate date;

}
