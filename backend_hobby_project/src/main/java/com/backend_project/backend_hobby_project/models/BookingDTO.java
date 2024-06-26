package com.backend_project.backend_hobby_project.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingDTO {
    private LocalTime time;
    private LocalDate date;
    private List<Long> userIds;
    private long venueId;
    private long hobbyId;

    public BookingDTO(String time, String date, List<Long> userIds, long venueId, long hobbyId) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time,formatTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(date,formatter);
        this.userIds = userIds;
        this.venueId = venueId;
        this.hobbyId = hobbyId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(String time) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time,formatTime);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(date,formatter);
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public BookingDTO() {
    }

}
