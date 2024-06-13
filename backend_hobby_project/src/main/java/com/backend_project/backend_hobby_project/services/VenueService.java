package com.backend_project.backend_hobby_project.services;


import com.backend_project.backend_hobby_project.models.Venue;
import com.backend_project.backend_hobby_project.models.VenueDTO;
import com.backend_project.backend_hobby_project.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {


    @Autowired
    VenueRepository venueRepository;


    public List<Venue> findAllVenues() {
        return this.venueRepository.findAll();
    }

    public Optional<Venue> findVenueById(long id) {
        return this.venueRepository.findById(id);
    }



    public Venue addVenue(Venue venue) {
        this.venueRepository.save(venue);
        return venue;
    }

    public Long deleteVenueById(long id) {
        this.venueRepository.deleteById(id);
        return id;
    }

    public Venue deleteVenue(Venue venue) {
        this.venueRepository.delete(venue);
        return venue;
    }

    public Venue updateVenue(VenueDTO venueDTO, long id) {
        String venueName = venueDTO.getName();
        String venueLocation = venueDTO.getLocation();
        int venueCapacity = venueDTO.getCapacity();


        Venue existingVenue = this.findVenueById(id).get();

        existingVenue.setName(venueName);
        existingVenue.setLocation(venueLocation);
        existingVenue.setCapacity(venueCapacity);


        venueRepository.save(existingVenue);

        return existingVenue;
    }

    public Venue updateVenueProp(VenueDTO venueDTO, long id, String property) {
        Venue venueToUpdate = this.findVenueById(id).get();
        switch (property) {
            case "name":
                venueToUpdate.setName(venueDTO.getName());
                break;
            case "location":
                venueToUpdate.setLocation(venueDTO.getLocation());
                break;
            case "capacity":
                venueToUpdate.setCapacity(venueDTO.getCapacity());
                break;
         
            case "default":
        }

        venueRepository.save(venueToUpdate);

        return venueToUpdate;
    }
}