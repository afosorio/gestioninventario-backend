package com.co.flypass.gestioninventario.domain.product;

public enum ProductEventType {
    ADD,
    REMOVE;

    @Override
    public String toString() {
        return switch (this) {
            case ADD -> "ADD";
            case REMOVE -> "REMOVE";
        };
    }
}