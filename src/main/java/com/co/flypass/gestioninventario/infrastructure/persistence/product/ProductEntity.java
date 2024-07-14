package com.co.flypass.gestioninventario.infrastructure.persistence.product;

import com.co.flypass.gestioninventario.infrastructure.persistence.cateogry.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private long id;
    private String name;
    private CategoryEntity categoryEntity;
    private double price;
    private Integer stockQuantity;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
