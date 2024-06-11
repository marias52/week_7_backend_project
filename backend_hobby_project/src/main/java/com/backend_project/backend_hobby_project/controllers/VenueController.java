package com.backend_project.backend_hobby_project.controllers;
import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.models.Venue;
import com.backend_project.backend_hobby_project.models.VenueDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import com.backend_project.backend_hobby_project.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("venues")
public class VenueController {

    @Autowired
    VenueService venueService;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues(){
        return new ResponseEntity<>(venueService.findAllVenues(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Venue> getVenue(@PathVariable Long id) {
        if (venueService.findVenueById(id).isPresent()) {
            return new ResponseEntity<>(venueService.findVenueById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Venue> addVenue (@RequestBody Venue venue){
        return new ResponseEntity<>(venueService.addVenue(venue), HttpStatus.CREATED);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Venue> updateVenue (@RequestBody VenueDTO venueDTO, @PathVariable Long id) {
        if (venueService.findVenueById(id).isPresent()) {
            return new ResponseEntity<>(venueService.updateVenue(venueDTO, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Long> deleteVenue (@PathVariable Long id){
        if( venueService.findVenueById(id).isPresent()) {
            bookingService.removeVenueFromAllBookings(id);

            return new ResponseEntity<>(venueService.deleteVenueById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<Venue> updateVenueProp (@RequestBody VenueDTO venueDTO, @PathVariable Long id, @RequestParam String property){
        if( venueService.findVenueById(id).isPresent()) {
            return new ResponseEntity<>(venueService.updateVenueProp(venueDTO, id, property), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}









