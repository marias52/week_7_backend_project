package com.backend_project.backend_hobby_project.components;

import com.backend_project.backend_hobby_project.models.*;
import com.backend_project.backend_hobby_project.repositories.BookingRepository;
import com.backend_project.backend_hobby_project.services.HobbyService;
import com.backend_project.backend_hobby_project.services.UserService;
import com.backend_project.backend_hobby_project.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    HobbyService hobbyService;

    @Autowired
    VenueService venueService;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Hobby fiveAside = new Hobby("Five a Side");
        hobbyService.addHobby(fiveAside);

        Venue wembly = new Venue("Wembly", "Wembly", 90000);
        venueService.addVenue(wembly);

        User sunny = new User("Sunny", 26, "Birmingham", "Lorem Ipsum", false);
        userService.addUser(sunny);
        User dan = new User("Dan", 22, "Coventry", "Lorem Ipsum", false);
        userService.addUser(dan);
        User maria = new User("Maria", 25, "London", "Lorem Ipsum", true);
        userService.addUser(maria);

        userService.addHobbyToUser(fiveAside.getId(), sunny.getId());
        userService.addHobbyToUser(fiveAside.getId(), dan.getId());
        userService.addHobbyToUser(fiveAside.getId(), maria.getId());

        List<User> users = new ArrayList<>();
        users.add(sunny);
        users.add(dan);
        users.add(maria);

        Booking booking = new Booking("18:00", "11/06/2024", wembly, fiveAside);
        booking.setUsers(users);
        bookingRepository.save(booking);
    }
}
