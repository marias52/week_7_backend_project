package com.backend_project.backend_hobby_project.controllers;

import com.backend_project.backend_hobby_project.enums.DaysOfTheWeek;
import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.models.UserDTO;
import com.backend_project.backend_hobby_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        if(userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        if(userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(userService.updateUser(userDTO, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updateUserProp(@RequestBody UserDTO userDTO, @PathVariable Long id, @RequestParam String property) {
        if(userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(userService.updateUserProp(userDTO, id, property), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (value = "/private")
    public ResponseEntity<List<User>> getPrivateUsers() {
         {
            return new ResponseEntity<>(userService.getPrivateUsers(), HttpStatus.OK);
        }
    }

    @GetMapping (value = "/public")
    public ResponseEntity<List<User>> getAllPublicUsers() {
            return new ResponseEntity<>(userService.getPublicUsers(), HttpStatus.OK);
    }

//
//    @GetMapping (value = "/{id}/availability")
//    public ResponseEntity<Optional<DaysOfTheWeek>> getUserAvailabilityById(@PathVariable Long id @PathVariable String availabilityk){
//        if (availability != null){
//            return new ResponseEntity<>(availability, HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
//        }
//    }
}
