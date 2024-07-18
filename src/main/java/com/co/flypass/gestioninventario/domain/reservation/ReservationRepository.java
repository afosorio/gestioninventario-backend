package com.co.flypass.gestioninventario.domain.reservation;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.infrastructure.persistence.product.ProductEntity;

import java.util.Optional;

public interface ReservationRepository {

    void save(Reservation reservation) ;

    void update(Reservation reservation) ;

    Optional<Reservation> findReservationById(long id);
}
