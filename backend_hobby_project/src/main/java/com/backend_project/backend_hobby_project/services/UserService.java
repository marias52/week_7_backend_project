package com.backend_project.backend_hobby_project.services;


import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.models.UserDTO;
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

    public User addUser(User user){
        this.userRepository.save(user);
        return user;
    }

    public long deleteUserById (long id){
        this.userRepository.deleteById(id);
        return id;
    }

    public void deleteUser (User user){
        this.userRepository.delete(user);
    }

    public void addHobbyToUser(Hobby hobby, User user) {
        List<Hobby> userHobbies = user.getHobbies();
        userHobbies.add(hobby);
        user.setHobbies(userHobbies);
        userRepository.save(user);
    }

    public void removeHobbyFromUser(Hobby hobby) {

    }

    public User updateUser (UserDTO userDTO, long id){
        String newName = userDTO.getName();
        int newAge = userDTO.getAge();
        String newLocation = userDTO.getLocation();
        String newBio = userDTO.getBiography();

        User existingUser = this.findUserById(id).get();
        existingUser.setName(newName);
        existingUser.setAge(newAge);
        existingUser.setLocation(newLocation);
        existingUser.setBiography(newBio);

        userRepository.save(existingUser);

        return existingUser;
    }

    public User updateUserProp (UserDTO userDTO, long id, String property) {
        User userToUpdate = this.findUserById(id).get();
        switch (property) {
            case "name":
                userToUpdate.setName(userDTO.getName());
                break;
            case "age":
                userToUpdate.setAge(userDTO.getAge());
                break;
            case "location":
                userToUpdate.setLocation(userDTO.getLocation());
                break;
            case "biography":
                userToUpdate.setBiography(userDTO.getBiography());
                break;
            case "hobby":
//                userToUpdate.setHobbies();
            default:
                break;
        }

        userRepository.save(userToUpdate);

        return userToUpdate;
    }
}
