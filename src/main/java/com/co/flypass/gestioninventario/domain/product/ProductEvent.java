package com.co.flypass.gestioninventario.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private ProductEventType typeEvent;
    private Product product;
    private int quantity;
}
