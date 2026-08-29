package org.mjvera.domain.valueobject;

public record Address(String street, String city, String state) {
    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or blank");
        }
    }

    public String toString() {
        return street + ", " + city + ", " + state;
    }
}
