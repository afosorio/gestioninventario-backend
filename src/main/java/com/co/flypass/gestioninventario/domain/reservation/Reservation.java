package com.co.flypass.gestioninventario.domain.reservation;

import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private int id;
    private Product product;
    private int quantity;
    private Customer customer;
    private LocalDate reservationDate;
    private String status;
}