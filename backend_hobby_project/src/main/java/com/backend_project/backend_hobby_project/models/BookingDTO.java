package com.backend_project.backend_hobby_project.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingDTO {
    private LocalTime time;
    private LocalDate date;
    private List<Long> userIds;
    private Long venueId;
    private Long hobbyId;

    public BookingDTO(String time, String date, List<Long> userIds, Long venueId, Long hobbyId) {
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

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public BookingDTO() {
    }

}
