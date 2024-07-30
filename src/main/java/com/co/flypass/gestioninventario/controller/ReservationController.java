package com.co.flypass.gestioninventario.controller;

import com.co.flypass.gestioninventario.application.reservation.ReservationService;
import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import jakarta.servlet.http.HttpServletResponse;
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
    public Response<Object> createPReservation(@Valid @RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
        return new Response<>(HttpServletResponse.SC_OK, "producto creado exitosamente");
    }

    @PutMapping
    public Response<Object> cancelReservation(@RequestParam final long id) {
        reservationService.cancelReservation(id);
        return new Response<>(HttpServletResponse.SC_OK, "Producto Eliminado exitosamente");
    }
}