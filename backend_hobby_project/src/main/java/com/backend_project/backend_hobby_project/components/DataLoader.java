package com.backend_project.backend_hobby_project.components;

import com.backend_project.backend_hobby_project.models.BookingDTO;
import com.backend_project.backend_hobby_project.models.Hobby;
import com.backend_project.backend_hobby_project.models.User;
import com.backend_project.backend_hobby_project.models.Venue;
import com.backend_project.backend_hobby_project.services.BookingService;
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
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Autowired
    HobbyService hobbyService;

    @Autowired
    VenueService venueService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User sunny = new User("Sunny", 26, "Birmingham", "Lorem Ipsum");
        userService.addUser(sunny);
        User dan = new User("Dan", 22, "Coventry", "Lorem Ipsum");
        userService.addUser(dan);
        User maria = new User("Maria", 25, "London", "Lorem Ipsum");
        userService.addUser(maria);

        Hobby fiveAside = new Hobby("Five a Side");
        hobbyService.addHobby(fiveAside);

        Venue wembly = new Venue("Wembly", "Wembly", 90000);
        venueService.addVenue(wembly);

        List<Long> userIds = new ArrayList<>();
        userIds.add(sunny.getId());
        userIds.add(dan.getId());
        userIds.add(maria.getId());

        BookingDTO bookingDTO = new BookingDTO("18:00", "11/06/2024", userIds, wembly.getId(), fiveAside.getId());
        bookingService.makeBooking(bookingDTO);
    }
}
