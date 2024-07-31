package com.co.flypass.gestioninventario.domain.customer;

import com.co.flypass.gestioninventario.domain.Constant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private long id;

    @NotBlank(message =  Constant.MESSAGE)
    private String name;

    @NotBlank(message =  Constant.MESSAGE)
    private String phone;

    @NotBlank(message =  Constant.MESSAGE)
    private String address;

    @NotBlank(message =  Constant.MESSAGE)
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
}
