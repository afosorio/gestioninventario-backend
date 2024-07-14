package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.ReservationService;
import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import com.co.flypass.gestioninventario.exception.NoDataFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationtById(@PathVariable(value = "id") long id) throws NoDataFoundException {
        Reservation reservation = reservationService.getReservationtById(id).orElseThrow(() -> new NoDataFoundException("Reservation not found"));
        return ResponseEntity.ok().body(reservation);
    }

    @PostMapping
    public Reservation createPReservation(@Valid @RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@Valid @RequestBody Reservation reservation) {
        Reservation updatedReservation= reservationService.updateReservation(reservation);
        return ResponseEntity.ok(updatedReservation);
    }

}
