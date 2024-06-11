package com.backend_project.backend_hobby_project.controllers;

import com.backend_project.backend_hobby_project.models.Booking;
import com.backend_project.backend_hobby_project.models.BookingDTO;
import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.models.HobbyDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import com.backend_project.backend_hobby_project.services.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("hobbies")
public class HobbyController {

    @Autowired
    HobbyService hobbyService;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Hobby>> getAllHobbies(){
        return new ResponseEntity<>(hobbyService.getAllHobbies(), HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Optional<Hobby>> getHobby(@PathVariable Long id){
        if(hobbyService.findHobbyById(id).isPresent()) {
            return new ResponseEntity<>(hobbyService.findHobbyById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Hobby> addHobby(@RequestBody Hobby hobby) {
        return new ResponseEntity<>(hobbyService.addHobby(hobby), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HobbyDTO> updateBooking(@RequestBody HobbyDTO hobbyDTO, @PathVariable Long id) {
        if (hobbyService.findHobbyById(id).isPresent()) {
            return new ResponseEntity<>(hobbyService.updateHobby (hobbyDTO, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Long> deleteHobby(@PathVariable Long id){
        if(hobbyService.findHobbyById(id).isPresent()) {
            bookingService.removeHobbyFromAllBookings(id);

            hobbyService.deleteHobbyById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
