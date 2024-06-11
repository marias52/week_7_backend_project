package com.backend_project.backend_hobby_project.services;


import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;


    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }


    public Optional<User> findUserById( long id) {
        return this.userRepository.findById(id);
    }

    public void addUser (User user){
        this.userRepository.save(user);
    }

    public void deleteUserById (long id){
        this.userRepository.deleteById(id);
    }

    public void deleteUser (User user){
        this.userRepository.delete(user);
    }

    public void updateUser (User user, long id){
        String newName = user.getName();
        int newAge = user.getAge();
        String newLocation = user.getLocation();
        String newBio = user.getBiography();

        User existingUser = this.findUserById(id).get();
        existingUser.setName(newName);
        existingUser.setAge(newAge);
        existingUser.setLocation(newLocation);
        existingUser.setBiography(newBio);

    }













}
