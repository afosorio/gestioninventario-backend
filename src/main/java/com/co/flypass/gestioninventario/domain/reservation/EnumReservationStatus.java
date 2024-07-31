package com.co.flypass.gestioninventario.domain.reservation;

public enum EnumReservationStatus {

    CONFIRMED,
    CANCELLED,
    SHIPPED;

    @Override
    public String toString() {
        return switch (this) {
            case CONFIRMED -> "Confirmed";
            case CANCELLED -> "Cancelled";
            case SHIPPED -> "Shipped";
        };
    }
}