package org.mjvera.domain.valueobject;

public record VenueName(String value) {
    public VenueName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Venue name cannot be null or blank");
        }
    }
}
