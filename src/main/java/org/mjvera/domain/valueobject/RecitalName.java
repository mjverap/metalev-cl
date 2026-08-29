package org.mjvera.domain.valueobject;

import org.mjvera.domain.exception.InvalidRecitalInfoException;

public record RecitalName(String value) {
    public RecitalName {
        if (value == null || value.isBlank()) {
            throw new InvalidRecitalInfoException("Recital name cannot be null or blank");
        }
    }
}
