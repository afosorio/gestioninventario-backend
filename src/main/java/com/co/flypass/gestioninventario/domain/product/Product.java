package com.co.flypass.gestioninventario.domain.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long id;
    private String name;
    private Category category;
    private double price;
    private Integer stockQuantity;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
