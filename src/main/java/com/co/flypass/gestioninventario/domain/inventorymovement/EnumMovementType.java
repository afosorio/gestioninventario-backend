package com.co.flypass.gestioninventario.domain.inventorymovement;

public enum EnumMovementType {
    ENTRY,
    EXIT,
    TRANSFER;

    @Override
    public String toString() {
        return switch (this) {
            case ENTRY -> "Entry";
            case EXIT -> "Exit";
            case TRANSFER -> "Transfer";
        };
    }
}