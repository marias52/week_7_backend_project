package com.backend_project.backend_hobby_project.controllers;

import com.backend_project.backend_hobby_project.enums.DaysOfTheWeek;
import com.backend_project.backend_hobby_project.exceptions.BadJSONException;
import com.backend_project.backend_hobby_project.exceptions.RequestNotFoundException;
import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.models.UserDTO;
import com.backend_project.backend_hobby_project.services.BookingService;
import com.backend_project.backend_hobby_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) throws Exception {

        Optional<User> user = userService.findUserById(id);

        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new RequestNotFoundException("User ID: " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(userService.updateUser(userDTO, id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not update user: " + id + " as user was not found");
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) throws Exception {
        try {
            bookingService.removeUserFromAllBookings(id);
            return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("User ID: " + id + " could not be deleted as it was not found");
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updateUserProp(@RequestBody UserDTO userDTO, @PathVariable Long id, @RequestParam String property) throws Exception {

        try {
            return new ResponseEntity<>(userService.updateUserProp(userDTO, id, property), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("Could not update user: " + id + " as user was not found");
        } catch (HttpMessageNotReadableException e) {
            throw new BadJSONException(e.getMessage());
        }
    }

    @GetMapping (value = "/private")
    public ResponseEntity<List<User>> getPrivateUsers() throws Exception {

        return new ResponseEntity<>(userService.getPrivateUsers(), HttpStatus.OK);

    }

    @GetMapping (value = "/public")
    public ResponseEntity<List<User>> getAllPublicUsers() throws Exception {

        return new ResponseEntity<>(userService.getPublicUsers(), HttpStatus.OK);

    }

    @GetMapping (value = "/availability/{id}")
    public ResponseEntity<List<DaysOfTheWeek>> getUserAvailabilityById(@PathVariable Long id) throws Exception {

        try {

            List<DaysOfTheWeek> availability = userService.getUserAvailabilityById(id);
            return new ResponseEntity<>(availability, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            throw new RequestNotFoundException("User ID: " + id + " not found");
        }

    }
}
