package com.backend_project.backend_hobby_project.services;

import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.models.HobbyDTO;
import com.backend_project.backend_hobby_project.repositories.HobbyRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyService {

    @Autowired
    HobbyRepository hobbyRepository;


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

    public HobbyDTO updateHobby(HobbyDTO hobbyDTO, Long id) {
        String hobbyName = hobbyDTO.getName();
        Hobby hobbyToUpdate = this.findHobbyById(id).get();

        hobbyToUpdate.setName(hobbyName);
        hobbyRepository.save(hobbyToUpdate);

        return hobbyDTO;
    }

    public boolean checkForCloseSpelling(String str1, String str2) {
        LevenshteinDistance calculateLevenshteinDistance = new LevenshteinDistance();
        double lfd = calculateLevenshteinDistance.apply(str1, str2);
        double ratioSimilarity = (1 - (lfd) / (Math.max(str1.length(), str2.length())));

        if (ratioSimilarity <= (0.8)) {
            return false;
        } else {
            return true;
        }
    }

    public Hobby addHobby(Hobby newHobby) {
        String newHobbyName = newHobby.getName();

        boolean shouldSave = true;

        for (Hobby hobby : hobbyRepository.findAll()) {
            String hobbyNameToCheck = hobby.getName();

            if (this.checkForCloseSpelling(newHobbyName, hobbyNameToCheck)) {
                shouldSave = false;
                break;
            }
        }

        if (shouldSave) {
            hobbyRepository.save(newHobby);
            return newHobby;
        }

        return null;
    }

}
