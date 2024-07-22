package com.co.flypass.gestioninventario.infrastructure.persistence.reservation;

import com.co.flypass.gestioninventario.domain.product.Product;
import com.co.flypass.gestioninventario.domain.reservation.EnumReservationStatus;
import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import com.co.flypass.gestioninventario.infrastructure.persistence.cateogry.CategoryEntity;
import com.co.flypass.gestioninventario.infrastructure.persistence.customer.CustomerEntity;
import com.co.flypass.gestioninventario.infrastructure.persistence.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_reservations")
public class ReservationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @Column(name = "name")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customer;

    @Column(name = "reservationDate")
    private LocalDate reservationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnumReservationStatus status;

    public static ReservationEntity fromDomain(final Reservation reservation) {
        return new ReservationEntity(
                reservation.getId(),
                ProductEntity.fromDomain(reservation.getProduct()),
                reservation.getQuantity(),
                CustomerEntity.fromDomain(reservation.getCustomer()),
                reservation.getReservationDate(),
                reservation.getStatus()
        );
    }

    public Reservation toDomain() {
        return new Reservation(
                this.id,
                this.product.toDomain(),
                this.quantity,
                this.customer.toDomain(),
                this.reservationDate,
                this.status
        );
    }
}
