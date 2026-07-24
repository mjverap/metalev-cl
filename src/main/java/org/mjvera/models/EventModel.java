package org.mjvera.models;

import org.mjvera.exceptions.InvalidDateRangeException;
import org.mjvera.exceptions.InvalidPriceRangeException;

import java.time.LocalDate;
import java.util.List;

public class EventModel {
    private String name;
    private int minTicketPrice;
    private int maxTicketPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String venue;
    private List<String> bands;

    public void setPriceRange(int minTicketPrice, int maxTicketPrice) {
        if (minTicketPrice > maxTicketPrice) {
            throw new InvalidPriceRangeException("Minimum ticket price cannot be greater that maximum ticket price.");
        }
        this.minTicketPrice = minTicketPrice;
        this.maxTicketPrice = maxTicketPrice;
    }

    public void setDateRange(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("Start date cannot be after end date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventModel() {}

    public EventModel(String name, String venue, List<String> bands) {
        this.name = name;
        this.venue = venue;
        this.bands = bands;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public List<String> getBands() {
        return bands;
    }
}
