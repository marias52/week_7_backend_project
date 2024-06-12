package com.backend_project.backend_hobby_project.services;


import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.models.UserDTO;
import com.backend_project.backend_hobby_project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HobbyService hobbyService;

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

    public long deleteUserById (Long id){
        this.userRepository.deleteById(id);
        return id;
    }

    public void deleteUser(User user){
        this.userRepository.delete(user);
    }

    public void setUserName(String name, Long userId) {
        User user = this.findUserById(userId).get();
        user.setName(name);
        userRepository.save(user);
    }

    public void setUserAge(int age, Long userId) {
        User user = this.findUserById(userId).get();
        user.setAge(age);
        userRepository.save(user);
    }

    public void setUserLocation(String location, Long userId) {
        User user = this.findUserById(userId).get();
        user.setLocation(location);
        userRepository.save(user);
    }

    public void setUserBiography(String biography, Long userId) {
        User user = this.findUserById(userId).get();
        user.setBiography(biography);
        userRepository.save(user);
    }

    @Transactional
    public void addHobbyToUser(Long hobbyId, Long userId) {
        if(hobbyService.findHobbyById(hobbyId).isEmpty()) {
            return;
        }
        Hobby hobby = hobbyService.findHobbyById(hobbyId).get();
        User user = this.findUserById(userId).get();
        List<Hobby> userHobbies = user.getHobbies();
        userHobbies.add(hobby);
        user.setHobbies(userHobbies);
        userRepository.save(user);
    }

    public void removeHobbyFromUser(Long hobbyId, Long userId) {
        if(hobbyService.findHobbyById(hobbyId).isEmpty()) {
            return;
        }
        Hobby hobby = hobbyService.findHobbyById(hobbyId).get();
        User user = this.findUserById(userId).get();
        List<Hobby> userHobbies = user.getHobbies();
        userHobbies.remove(hobby);
        user.setHobbies(userHobbies);
        userRepository.save(user);
    }

    public User updateUser (UserDTO userDTO, Long id){
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

    public User updateUserProp (UserDTO userDTO, Long userId, String property) {

        if(this.findUserById(userId).isEmpty()) {
            return null;
        }

        switch (property) {
            case "name":
                this.setUserName(userDTO.getName(), userId);
                break;
            case "age":
                this.setUserAge(userDTO.getAge(), userId);
                break;
            case "location":
                this.setUserLocation(userDTO.getLocation(), userId);
                break;
            case "biography":
                this.setUserBiography(userDTO.getBiography(), userId);
                break;
            case "addHobby":
                for(Long hobbyId : userDTO.getHobbyIds()) {
                    this.addHobbyToUser(hobbyId, userId);
                }
                break;
            case "removeHobby":
                for(Long hobbyId : userDTO.getHobbyIds()) {
                    this.removeHobbyFromUser(hobbyId, userId);
                }
                break;
            default:
                break;
        }

        User userToUpdate = this.findUserById(userId).get();

        return userToUpdate;
    }
}
