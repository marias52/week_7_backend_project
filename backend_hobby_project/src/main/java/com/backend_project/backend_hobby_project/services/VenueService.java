package com.backend_project.backend_hobby_project.services;


import com.backend_project.backend_hobby_project.models.Venue;
import com.backend_project.backend_hobby_project.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {


    @Autowired
    VenueRepository venueRepository;


    public List<Venue> findAllVenues(){
        return this.venueRepository.findAll();
    }

    public Optional<Venue> findVenueById(long id){
        return this.venueRepository.findById(id);
    }

    public void addVenue(Venue venue){
        this.venueRepository.save(venue);
    }

    public void deleteVenueById(long id){
        this.venueRepository.deleteById(id);
    }

    public void deleteVenue(Venue venue){
        this.venueRepository.delete(venue);
    }

    public void updateVenue(Venue venue, long id){
        String newLocation = venue.getLocation();
        int newCapacity = venue.getCapacity();

        Venue existingVenue = this.findVenueById(id).get();

        existingVenue.setLocation(newLocation);
        existingVenue.setCapacity(newCapacity);



    }






}
