package com.co.flypass.gestioninventario.infrastructure.persistence.product;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.infrastructure.persistence.cateogry.CategoryEntity;
import jakarta.persistence.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_products")
public class ProductEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @Column(name = "price")
    private double price;

    @Column(name = "stockQuantity")
    private Integer stockQuantity;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "updatedDate")
    private LocalDate updatedDate;

    public static ProductEntity fromDomain(final Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                Objects.nonNull(product.getCategory()) ?CategoryEntity.fromDomain(product.getCategory()) : null,
                product.getPrice(),
                product.getStockQuantity(),
                product.getCreatedDate(),
                product.getUpdatedDate()
        );
    }

    public Product toDomain() {
        return new Product(
                this.id,
                this.name,
                this.category.toDomain(),
                this.price,
                this.stockQuantity,
                this.createdDate,
                this.updatedDate
        );
    }
}
