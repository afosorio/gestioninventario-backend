package com.co.flypass.gestioninventario.domain.reservation;

public enum EnumReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    SHIPPED,
    DELIVERED,
    RETURNED;

    @Override
    public String toString() {
        return switch (this) {
            case PENDING -> "Pending";
            case CONFIRMED -> "Confirmed";
            case CANCELLED -> "Cancelled";
            case SHIPPED -> "Shipped";
            case DELIVERED -> "Delivered";
            case RETURNED -> "Returned";
        };
    }
}