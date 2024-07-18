package com.co.flypass.gestioninventario.infrastructure.persistence.cateogry;

import com.co.flypass.gestioninventario.domain.cateogry.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_categories")
public class CategoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public static CategoryEntity fromDomain(final Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category toDomain() {
        return new Category(
                this.id,
                this.name,
                this.description
        );
    }
}
