package com.co.flypass.gestioninventario.infrastructure.persistence.inventorymovement;

import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovementRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMovementRepositoryImpl extends ListCrudRepository<InventoryMovementEntity, Long>, InventoryMovementRepository {

    @Override
    default void save(InventoryMovement movement) {
        save(InventoryMovementEntity.fromDomain(movement));
    }

    @Override
    default List<InventoryMovement> findAllMovements() {
        return findAll().stream().map(InventoryMovementEntity::toDomain).toList();
    }
}
