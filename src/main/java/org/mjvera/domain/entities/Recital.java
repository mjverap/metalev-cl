package org.mjvera.domain.entities;

import org.mjvera.domain.exception.InvalidDateRangeException;
import org.mjvera.domain.exception.InvalidPriceRangeException;
import org.mjvera.domain.exception.InvalidRecitalInfoException;
import org.mjvera.domain.valueobject.BandList;
import org.mjvera.domain.valueobject.DateRange;
import org.mjvera.domain.valueobject.PriceRange;
import org.mjvera.domain.valueobject.RecitalName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Recital {
    private final String id;
    private String name;
    private Venue venue;
    private final List<String> bands;
    private PriceRange ticketPriceRange;
    private DateRange dateRange;

    public Recital() {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = "";
        this.venue = null;
        this.bands = new ArrayList<>();
    }

    public Recital(String name, Venue venue, List<String> bands) {
        if (venue == null) {
            throw new InvalidRecitalInfoException("Invalid recital info.");
        }
        try {
            this.id = java.util.UUID.randomUUID().toString();
            this.name = new RecitalName(name).value();
            this.venue = venue;
            this.bands = new ArrayList<>(new BandList(bands).value());
        } catch (IllegalArgumentException exception) {
            throw new InvalidRecitalInfoException("Invalid recital info.");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Venue getVenue() {
        return venue;
    }

    public List<String> getBands() {
        return new ArrayList<>(bands);
    }

    public PriceRange getTicketPriceRange() {
        return this.ticketPriceRange;
    }

    public int getMinTicketPrice() {
        return this.ticketPriceRange == null ? 0 : this.ticketPriceRange.minPrice();
    }

    public int getMaxTicketPrice() {
        return this.ticketPriceRange == null ? 0 : this.ticketPriceRange.maxPrice();
    }

    public DateRange getDateRange() {
        return this.dateRange;
    }

    public LocalDate getStartDate() {
        return this.dateRange == null ? null : this.dateRange.startDate();
    }

    public LocalDate getEndDate() {
        return this.dateRange == null ? null : this.dateRange.endDate();
    }

    public void renameTo(String newName) {
        this.name = new RecitalName(newName).value();
    }

    public void moveTo(Venue newVenue) {
        if (newVenue == null) {
            throw new InvalidRecitalInfoException("Invalid recital info.");
        }
        this.venue = newVenue;
    }

    public void addBand(String bandName) {
        if (bandName == null || bandName.isBlank()) {
            throw new InvalidRecitalInfoException("Invalid recital info.");
        }
        this.bands.add(bandName);
    }

    public void removeBand(String bandName) {
        if (bandName == null || bandName.isBlank()) {
            throw new InvalidRecitalInfoException("Invalid recital info.");
        }
        this.bands.remove(bandName);
    }

    public void updateTicketPriceRange(int minTicketPrice, int maxTicketPrice) {
        try {
            this.ticketPriceRange = new PriceRange(minTicketPrice, maxTicketPrice);
        } catch (IllegalArgumentException exception) {
            throw new InvalidPriceRangeException("Minimum ticket price cannot be greater that maximum ticket price.");
        }
    }

    public void reprogramTo(LocalDate startDate, LocalDate endDate) {
        try {
            this.dateRange = new DateRange(startDate, endDate);
        } catch (IllegalArgumentException exception) {
            throw new InvalidDateRangeException("Start date cannot be after end date.");
        }
    }
}
