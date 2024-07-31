package com.co.flypass.gestioninventario.infrastructure.persistence.inventorymovement;

import com.co.flypass.gestioninventario.domain.inventorymovement.EnumMovementType;
import com.co.flypass.gestioninventario.domain.inventorymovement.InventoryMovement;
import com.co.flypass.gestioninventario.domain.product.ProductEventType;
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
@Table(name="tb_inventory_movements")
public class InventoryMovementEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @Column(name = "type")
    private String type;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "date")
    private LocalDate date;


    public static InventoryMovementEntity fromDomain(final InventoryMovement inventoryMovement) {
        return new InventoryMovementEntity(
                inventoryMovement.getId(),
                ProductEntity.fromDomain(inventoryMovement.getProduct()),
                inventoryMovement.getType().name(),
                inventoryMovement.getQuantity(),
                inventoryMovement.getDate()
        );
    }

    public InventoryMovement toDomain() {
        return new InventoryMovement(
                this.id,
                this.product.toDomain(),
                ProductEventType.valueOf(this.type),
                this.quantity,
                this.date
        );
    }
}
