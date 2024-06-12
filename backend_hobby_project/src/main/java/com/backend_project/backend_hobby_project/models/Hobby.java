package com.backend_project.backend_hobby_project.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "hobbies")
public class Hobby {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @JsonIgnoreProperties( {"hobbies", "bookings"})
    @ManyToMany(mappedBy = "hobbies")
    private List<User> users;

    @JsonIgnoreProperties({"hobby"})
    @OneToMany (mappedBy = "hobby")
    private List<Booking> bookings;

    public Hobby(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public Hobby() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
