package com.backend_project.backend_hobby_project.services;

import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.repositories.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyService {

    @Autowired
    HobbyRepository hobbyRepository;

    public void addHobby(Hobby hobby) {
        hobbyRepository.save(hobby);
    }

    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    public Optional<Hobby> findHobbyById(Long id) {
        return hobbyRepository.findById(id);
    }

    public void deleteHobbyById(Long id) {
        hobbyRepository.deleteById(id);
    }

    public void deleteHobbyByEntity(Hobby hobby) {
        hobbyRepository.delete(hobby);
    }

    public void updateHobby(Hobby hobby, Long id) {
        String hobbyName = hobby.getName();
        Hobby hobbyToUpdate = this.findHobbyById(id).get();

        hobbyToUpdate.setName(hobbyName);
        hobbyRepository.save(hobbyToUpdate);
    }
}
