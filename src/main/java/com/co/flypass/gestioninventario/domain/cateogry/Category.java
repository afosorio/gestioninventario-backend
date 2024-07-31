package com.co.flypass.gestioninventario.domain.cateogry;


import com.co.flypass.gestioninventario.domain.Constant;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message =  Constant.MESSAGE)
    private String name;

    @NotBlank(message =  Constant.MESSAGE)
    private String description;
}
