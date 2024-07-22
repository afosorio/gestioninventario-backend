package com.co.flypass.gestioninventario.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent {
    private ProductEventType typeEvent;
    private Product product;
}
