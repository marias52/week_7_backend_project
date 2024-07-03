package com.backend_project.backend_hobby_project.controllers;

import com.backend_project.backend_hobby_project.exceptions.BadJSONException;
import com.backend_project.backend_hobby_project.exceptions.BadRequestException;
import com.backend_project.backend_hobby_project.exceptions.RequestNotFoundException;
import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.models.HobbyDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import com.backend_project.backend_hobby_project.services.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping ("hobbies")
public class HobbyController {

    @Autowired
    HobbyService hobbyService;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Hobby>> getAllHobbies() throws Exception {
        List<Hobby> hobbies = hobbyService.getAllHobbies();
        return new ResponseEntity<>(hobbies, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Optional<Hobby>> getHobby(@PathVariable Long id) throws Exception {

        Optional<Hobby> hobby = hobbyService.findHobbyById(id);

        if(hobby.isPresent()) {
            return new ResponseEntity<>(hobby, HttpStatus.OK);
        } else {
            throw new RequestNotFoundException("Hobby ID: " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Hobby> addHobby(@RequestBody Hobby hobby) throws Exception {
        try {
            Hobby newHobby = hobbyService.addHobby(hobby);
            if (newHobby != null) {
                return new ResponseEntity<>(newHobby, HttpStatus.CREATED);
            } else {
                throw new BadRequestException("Hobby not created - Hobby already exists or similar spelling detected");
            }
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HobbyDTO> updateBooking(@RequestBody HobbyDTO hobbyDTO, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(hobbyService.updateHobby (hobbyDTO, id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not find hobby: " + id);
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Long> deleteHobby(@PathVariable Long id){
        try {
            bookingService.removeHobbyFromAllBookings(id);
            hobbyService.deleteHobbyById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Hobby ID: " + id + " could not be deleted as it was not found");
        }
    }

}
