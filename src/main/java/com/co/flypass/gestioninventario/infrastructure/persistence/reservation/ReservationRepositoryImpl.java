package com.co.flypass.gestioninventario.infrastructure.persistence.reservation;

import com.co.flypass.gestioninventario.domain.reservation.ReservationRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositoryImpl extends CrudRepository<ReservationEntity, Long>, ReservationRepository {
}
