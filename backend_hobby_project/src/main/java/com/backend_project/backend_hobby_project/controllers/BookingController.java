package com.backend_project.backend_hobby_project.controllers;

import com.backend_project.backend_hobby_project.models.Booking;
import com.backend_project.backend_hobby_project.models.BookingDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.findAllBookings(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Booking>> getBooking(@PathVariable Long id) {
        if(bookingService.findBookingById(id).isPresent()) {
            return new ResponseEntity<>(bookingService.findBookingById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Booking> makeBooking(@RequestBody BookingDTO bookingDTO) {
        return new ResponseEntity<>(bookingService.makeBooking(bookingDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody BookingDTO bookingDTO, @PathVariable Long id) {
        if (bookingService.findBookingById(id).isPresent()) {
            return new ResponseEntity<>(bookingService.updateBooking(bookingDTO, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteBooking(@PathVariable Long id) {
        if(bookingService.findBookingById(id).isPresent()) {
            bookingService.deleteBooking(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
