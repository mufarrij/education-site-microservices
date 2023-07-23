package com.simplesolutions.courseservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Enum to represent availability status of the course
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
public enum Status {
    AVAILABLE("Available"), UNAVAILABLE("Unavailable");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    @JsonCreator
    public static Status getStatus(String value) {
        return Arrays.stream(Status.values()).
                filter(v -> v.toString().toUpperCase().equals(value.toUpperCase())).findFirst().orElse(null);
    }
}
