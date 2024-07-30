package com.co.flypass.gestioninventario.infrastructure.persistence.inventorymovement;

import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovementRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMovementRepositoryImpl extends CrudRepository<InventoryMovementEntity, Long>, InventoryMovementRepository {

    @Override
    default void save(InventoryMovement movement) {
        save(InventoryMovementEntity.fromDomain(movement));
    }
}
