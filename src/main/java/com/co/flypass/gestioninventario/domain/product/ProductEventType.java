package com.co.flypass.gestioninventario.domain.product;

public enum ProductEventType {
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

