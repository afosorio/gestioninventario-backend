package com.co.flypass.gestioninventario.domain.reservation;


import java.util.Optional;

public interface ReservationRepository {

    void save(Reservation reservation) ;

    void update(Reservation reservation) ;

    Optional<Reservation> findReservationById(long id);
}
