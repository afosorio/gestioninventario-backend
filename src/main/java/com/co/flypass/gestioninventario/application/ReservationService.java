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


    public void createReservation(Reservation reservation) {

        reservationRepository.save(reservation);
    }

    public void cancelReservation(long id) {

        Optional<Reservation> reservationOptional = reservationRepository.findReservationById(id);
        if(reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            reservation.setStatus("CANCEL");
            reservationRepository.update(reservation);
        }
    }
}
