package com.co.flypass.gestioninventario.infrastructure.persistence.reservation;

import com.co.flypass.gestioninventario.domain.reservation.Reservation;
import com.co.flypass.gestioninventario.domain.reservation.ReservationRepository;
import com.co.flypass.gestioninventario.infrastructure.persistence.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepositoryImpl extends ListCrudRepository<ReservationEntity, Long>,
        JpaSpecificationExecutor<ReservationEntity>, ReservationRepository {

    @Override
    default void save(Reservation reservation) {
        save(ReservationEntity.fromDomain(reservation));
    }

    @Override
    default void update(Reservation reservation) {
        save(reservation);
    }

    @Override
    default Optional<Reservation> findReservationById(long id) {
        return findById(id).map(ReservationEntity::toDomain);
    }
}
