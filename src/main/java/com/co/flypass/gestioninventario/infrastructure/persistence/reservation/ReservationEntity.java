package com.co.flypass.gestioninventario.infrastructure.persistence.reservation;

import com.co.flypass.gestioninventario.infrastructure.persistence.customer.CustomerEntity;
import com.co.flypass.gestioninventario.infrastructure.persistence.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

    private int id;
    private ProductEntity productEntity;
    private int quantity;
    private CustomerEntity customerEntity;
    private LocalDate reservationDate;
    private String status;
}
