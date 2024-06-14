package com.backend_project.backend_hobby_project.controllers;
import com.backend_project.backend_hobby_project.exceptions.BadJSONException;
import com.backend_project.backend_hobby_project.exceptions.RequestNotFoundException;
import com.backend_project.backend_hobby_project.models.Venue;
import com.backend_project.backend_hobby_project.models.VenueDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import com.backend_project.backend_hobby_project.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping ("venues")
public class VenueController {

    @Autowired
    VenueService venueService;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenues() throws Exception{
        List<Venue> venues = venueService.findAllVenues();
        return new ResponseEntity<>(venues, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Venue>> getVenue(@PathVariable Long id) throws Exception {

        Optional<Venue> venue = venueService.findVenueById(id);

        if (venue.isPresent()) {
            return new ResponseEntity<>(venue, HttpStatus.OK);
        } else {
            throw new RequestNotFoundException("Venue ID: " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Venue> addVenue (@RequestBody Venue venue) throws Exception {
        try {
            return new ResponseEntity<>(venueService.addVenue(venue), HttpStatus.CREATED);
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }

    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Venue> updateVenue (@RequestBody VenueDTO venueDTO, @PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(venueService.updateVenue(venueDTO, id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not update venue: " + id + " as venue could was not found");
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Long> deleteVenue (@PathVariable Long id){
        try {
            bookingService.removeVenueFromAllBookings(id);
            return new ResponseEntity<>(venueService.deleteVenueById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Venue ID: " + id + " could not be deleted as it was not found");
        }
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<Venue> updateVenueProp (@RequestBody VenueDTO venueDTO, @PathVariable Long id, @RequestParam String property){
        try {
            return new ResponseEntity<>(venueService.updateVenueProp(venueDTO, id, property), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not update venue: " + id + " as venue could was not found");
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }
}









