package com.backend_project.backend_hobby_project.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time")
    private String time;

    @Column(name = "date")
    private String date;


    @JsonIgnoreProperties({"bookings"})
    @ManyToMany
    @JoinTable(
            name = "user_bookings",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @JsonIgnoreProperties({"bookings"})
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @JsonIgnoreProperties({"bookings"})
    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;

    public Booking(String time, String date, Venue venue, Hobby hobby) {
        this.time = time;
        this.date = date;
        this.venue = venue;
        this.hobby = hobby;
        this.users = new ArrayList<>();
    }

    public Booking() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
