package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.reservation.ReservationService;
import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void createPReservation(@Valid @RequestBody Reservation reservation) {
         reservationService.createReservation(reservation);
    }

    @PutMapping
    public void cancelReservation(@RequestParam final long id) {
        reservationService.cancelReservation(id);
    }
}