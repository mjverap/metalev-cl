package org.mjvera.domain.valueobject;

import java.util.List;

public record BandList(List<String> value) {
    public BandList {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Band list cannot be null or empty");
        }
        boolean hasInvalidBandName = value.stream().anyMatch(band -> band == null || band.isBlank());
        if (hasInvalidBandName) {
            throw new IllegalArgumentException("Band list cannot contain null or blank names");
        }
        value = List.copyOf(value);
    }
}
