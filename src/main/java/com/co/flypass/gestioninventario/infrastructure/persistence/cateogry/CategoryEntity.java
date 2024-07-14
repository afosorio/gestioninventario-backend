package com.co.flypass.gestioninventario.infrastructure.persistence.cateogry;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    private long id;
    private String name;
    private String description;
}
