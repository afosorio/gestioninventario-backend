package com.co.flypass.gestioninventario.domain.product;

import com.co.flypass.gestioninventario.domain.Constant;
import com.co.flypass.gestioninventario.domain.cateogry.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private long id;

    @NotBlank(message =  Constant.MESSAGE)
    private String name;

    @NotNull(message =  Constant.MESSAGE)
    private Category category;

    @NotBlank(message =  Constant.MESSAGE)
    private double price;

    @NotBlank(message =  Constant.MESSAGE)
    private int stockQuantity;

    @NotNull(message =  Constant.MESSAGE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate createdDate;

    @NotNull(message =  Constant.MESSAGE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate updatedDate;

    @Version
    private int version;

    public Product(long id, String name, Category category, double price, int stockQuantity, LocalDate createdDate, LocalDate updatedDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
