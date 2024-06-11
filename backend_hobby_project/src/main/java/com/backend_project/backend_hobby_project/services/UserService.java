package com.backend_project.backend_hobby_project.services;


import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;


    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }














}
