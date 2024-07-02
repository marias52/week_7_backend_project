package com.backend_project.backend_hobby_project.models;


import java.util.List;

public class VenueDTO {
    private String name;
    private String location;
    private int capacity;

    private List<Long> bookingIds;

    public VenueDTO(String name, String location, int capacity, List<Long> bookingIds) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.bookingIds = bookingIds;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Long> getBookingIds() {
        return bookingIds;
    }

    public void setBookingIds(List<Long> bookingIds) {
        this.bookingIds = bookingIds;
    }

    public VenueDTO() {
    }


}