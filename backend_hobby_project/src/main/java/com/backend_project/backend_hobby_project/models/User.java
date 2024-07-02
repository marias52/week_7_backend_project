package com.backend_project.backend_hobby_project.models;

import com.backend_project.backend_hobby_project.enums.DaysOfTheWeek;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "location")
    private String location;

    @Column(name = "biography")
    private String biography;

    @Column (name = "private")
    private boolean isPrivate;

    @Enumerated(EnumType.STRING)
    @Column (name = "availability")
    private List<DaysOfTheWeek> availability;

    @JsonIgnoreProperties({"users"})
    @ManyToMany(mappedBy = "users")
    private List<Booking> bookings;

    @JsonIgnoreProperties({"users", "bookings"})
    @ManyToMany
    @JoinTable(
            name = "user_hobbies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private List<Hobby> hobbies;

    public User(String name, int age, String location, String biography, boolean isPrivate,List<DaysOfTheWeek> availability) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.biography = biography;
        this.isPrivate = isPrivate;
        this.availability = availability;
        this.hobbies = new ArrayList<>();
        this.bookings = new ArrayList<>();

    }

    public User() {
    }


   

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Booking> getBookings() {
        if (this.isPrivate){
            return null;
        }
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }


    public List<DaysOfTheWeek> getAvailability() {
        if(this.isPrivate){
            return null;
        }
        return availability;
    }

    public void setAvailability(List<DaysOfTheWeek> availability) {
        this.availability = availability;
    }
}
