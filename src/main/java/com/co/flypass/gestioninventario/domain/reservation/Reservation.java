package com.co.flypass.gestioninventario.domain.reservation;

import com.co.flypass.gestioninventario.domain.Constant;
import com.co.flypass.gestioninventario.domain.customer.Customer;
import com.co.flypass.gestioninventario.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private int id;

    @NotNull(message =  Constant.MESSAGE)
    private Product product;

    @NotNull(message =  Constant.MESSAGE)
    private int quantity;

    @NotNull(message =  Constant.MESSAGE)
    private Customer customer;

    @NotNull(message =  Constant.MESSAGE)
    private LocalDate reservationDate;

    @NotNull(message =  Constant.MESSAGE)
    private EnumReservationStatus status;
}