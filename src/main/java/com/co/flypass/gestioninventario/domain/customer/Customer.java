package com.co.flypass.gestioninventario.domain.customer;

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
    private String name;
    private String phone;
    private String address;
    private String email;
}
