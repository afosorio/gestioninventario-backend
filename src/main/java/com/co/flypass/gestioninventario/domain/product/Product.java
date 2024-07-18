package com.co.flypass.gestioninventario.domain.product;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate createdDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate updatedDate;
}
