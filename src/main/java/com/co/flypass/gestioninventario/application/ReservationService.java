package com.co.flypass.gestioninventario.application;

import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import com.co.flypass.gestioninventario.domain.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Optional<Reservation> getReservationtById(long id) {
        return Optional.of(new Reservation());
    }

    public Reservation createReservation(Reservation reservation) {
        return new Reservation();
    }

    public Reservation updateReservation(Reservation reservation) {
        return new Reservation();
    }

}
