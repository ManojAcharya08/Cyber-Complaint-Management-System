package com.example.cybercrime_management.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    NEW("New"),
    UNDER_INVESTIGATION("Under Investigation"),
    SOLVED("Solved"),
    CLOSED("Closed");

    private final String dbValue;

    Status(String dbValue) { this.dbValue = dbValue; }

    @JsonValue
    public String getDbValue() { return dbValue; }

    @JsonCreator
    public static Status fromDbValue(String value) {
        for (Status s : values()) {
            if (s.dbValue.equalsIgnoreCase(value)) return s;
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
