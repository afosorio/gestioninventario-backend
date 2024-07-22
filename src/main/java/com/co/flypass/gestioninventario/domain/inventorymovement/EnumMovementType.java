package com.co.flypass.gestioninventario.domain.inventorymovement;

public enum EnumMovementType {
    ENTRY,
    EXIT;

    @Override
    public String toString() {
        return switch (this) {
            case ENTRY -> "ENTRY";
            case EXIT -> "EXIT";
        };
    }
}