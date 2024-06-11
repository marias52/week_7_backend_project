package com.backend_project.backend_hobby_project.models;

import java.util.List;

public class BookingDTO {
    private String time;
    private String date;
    private List<Long> userIds;
    private long venueId;
    private long hobbyId;

    public BookingDTO(String time, String date, List<Long> userIds, long venueId, long hobbyId) {
        this.time = time;
        this.date = date;
        this.userIds = userIds;
        this.venueId = venueId;
        this.hobbyId = hobbyId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
