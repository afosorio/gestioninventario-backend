package com.co.flypass.gestioninventario.application.reservation;

import com.co.flypass.gestioninventario.application.product.ProductService;
import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.reservation.EnumReservationStatus;
import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import com.co.flypass.gestioninventario.domain.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ProductService productService;

    public ReservationService(ReservationRepository reservationRepository, ProductService productService) {
        this.reservationRepository = reservationRepository;
        this.productService = productService;
    }

    public void createReservation(Reservation reservation) {

        Product product = productService.getProductById(reservation.getProduct().getId());
        reservation.setStatus(EnumReservationStatus.CONFIRMED);
        reservationRepository.save(reservation);
        productService.removeStock(product, reservation.getQuantity());
    }

    public void cancelReservation(long id) {

        Optional<Reservation> reservationOptional = reservationRepository.findReservationById(id);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setStatus(EnumReservationStatus.CANCELLED);
            reservationRepository.update(reservation);
            Product product = productService.getProductById(reservation.getProduct().getId());
            productService.addStock(product, reservation.getQuantity());
        }
    }
}