package com.backend_project.backend_hobby_project.controllers;

import com.backend_project.backend_hobby_project.exceptions.BadJSONException;
import com.backend_project.backend_hobby_project.exceptions.RequestNotFoundException;
import com.backend_project.backend_hobby_project.models.Booking;
import com.backend_project.backend_hobby_project.models.BookingDTO;
import com.backend_project.backend_hobby_project.models.UserDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() throws Exception {
        List<Booking> bookings = bookingService.findAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Booking>> getBooking(@PathVariable Long id) throws Exception {

        Optional<Booking> booking = bookingService.findBookingById(id);

        if(booking.isPresent()) {
            return new ResponseEntity<>(bookingService.findBookingById(id), HttpStatus.OK);
        } else {
            throw new RequestNotFoundException("Booking ID: " + id + " not found");
        }

    }

    @PostMapping
    public ResponseEntity<Booking> makeBooking(@RequestBody BookingDTO bookingDTO) throws Exception {
        try {
            return new ResponseEntity<>(bookingService.makeBooking(bookingDTO), HttpStatus.CREATED);
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody BookingDTO bookingDTO, @PathVariable Long id) throws Exception{
        try {
            return new ResponseEntity<>(bookingService.updateBooking(bookingDTO, id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not update booking: " + id + " as it was not found");
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Booking> updateBookingProp(@RequestBody BookingDTO bookingDTO, @PathVariable Long id,@RequestParam String property) throws Exception {
        try {
            return new ResponseEntity<>(bookingService.updateBookingProp(bookingDTO, id,property), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not update booking: " + id + " as booking was not found");
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteBooking(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(bookingService.deleteBooking(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Booking ID: " + id + " could not be deleted as it was not found");
        }
    }

    @GetMapping(value = "/recommendations" )
    public ResponseEntity<List<Booking>> getRecommendations(@RequestBody UserDTO userDTO) throws Exception {
        List<Booking> recommendations = bookingService.recommendBookings(userDTO);
        return new ResponseEntity<>(recommendations,HttpStatus.OK);
    }
}
