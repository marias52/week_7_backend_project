package com.backend_project.backend_hobby_project.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "date")
    private LocalDate date;

    @JsonIgnoreProperties({"bookings", "hobbies"})
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

    @JsonIgnoreProperties({"bookings", "users"})
    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;

    public Booking(String time, String date, Venue venue, Hobby hobby) {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.time = LocalTime.parse(time,formatTime);
        this.date = LocalDate.parse(date,formatter);
        this.venue = venue;
        this.hobby = hobby;
        this.users = new ArrayList<>();
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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



}
