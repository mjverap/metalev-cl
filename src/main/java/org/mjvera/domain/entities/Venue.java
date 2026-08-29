package org.mjvera.domain.entities;

import org.mjvera.domain.valueobject.Address;
import org.mjvera.domain.valueobject.VenueName;

public class Venue {
    private final String id;
    private String name;
    private Address address;

    public Venue(String id, String name, Address address) {
        this.id = id;
        this.name = new VenueName(name).value();
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void renameTo(String newName) {
        this.name = new VenueName(newName).value();
    }

    public void updateAddress(Address newAddress) {
        this.address = newAddress;
    }
}
