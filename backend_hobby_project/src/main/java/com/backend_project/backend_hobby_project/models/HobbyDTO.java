package com.backend_project.backend_hobby_project.models;

import java.util.List;

public class HobbyDTO {
    private String name;
    private List<Long> userIds;
    private List<Long> bookingIds;

    public HobbyDTO(String name, List<Long> userIds, List<Long> bookingIds) {
        this.name = name;
        this.userIds = userIds;
        this.bookingIds = bookingIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getBookingIds() {
        return bookingIds;
    }

    public void setBookingIds(List<Long> bookingIds) {
        this.bookingIds = bookingIds;
    }

    public HobbyDTO() {
    }
}
