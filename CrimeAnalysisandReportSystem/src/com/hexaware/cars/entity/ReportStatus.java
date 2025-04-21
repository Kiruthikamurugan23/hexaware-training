package com.hexaware.cars.entity;

public enum ReportStatus {
    DRAFT,
    FINALIZED,
    OPEN,
    CLOSED;

    public static ReportStatus fromString(String value) {
        return ReportStatus.valueOf(value.toUpperCase());
    }
}
