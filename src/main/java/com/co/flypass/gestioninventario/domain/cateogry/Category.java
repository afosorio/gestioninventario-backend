package com.co.flypass.gestioninventario.domain.cateogry;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private long id;
    private String name;
    private String description;
}
