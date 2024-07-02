package com.backend_project.backend_hobby_project.components;

import com.backend_project.backend_hobby_project.enums.DaysOfTheWeek;
import com.backend_project.backend_hobby_project.models.*;
import com.backend_project.backend_hobby_project.repositories.BookingRepository;
import com.backend_project.backend_hobby_project.repositories.HobbyRepository;
import com.backend_project.backend_hobby_project.services.BookingService;
import com.backend_project.backend_hobby_project.services.HobbyService;
import com.backend_project.backend_hobby_project.services.UserService;
import com.backend_project.backend_hobby_project.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    VenueService venueService;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Hobby fiveAside = new Hobby("Five a Side");
        hobbyRepository.save(fiveAside);

        Venue wembly = new Venue("Wembly", "London", 90000);
        venueService.addVenue(wembly);

        List<DaysOfTheWeek> sunnyAvailability = new ArrayList<>();
        sunnyAvailability.add(DaysOfTheWeek.FRIDAYMORNING);
        sunnyAvailability.add(DaysOfTheWeek.TUESDAYEVENING);

        List<DaysOfTheWeek> danAvailability = new ArrayList<>();
        danAvailability.add(DaysOfTheWeek.MONDAYMORNING);
        danAvailability.add(DaysOfTheWeek.TUESDAYEVENING);

        List<DaysOfTheWeek> mariaAvailability = new ArrayList<>();
        mariaAvailability.add(DaysOfTheWeek.WEDNESDAYAFTERNOON);
        mariaAvailability.add(DaysOfTheWeek.TUESDAYEVENING);

        User sunny = new User("Sunny", 26, "Birmingham", "Lorem Ipsum", false, sunnyAvailability);
        userService.addUser(sunny);
        User dan = new User("Dan", 22, "Coventry", "Lorem Ipsum", false, danAvailability);
        userService.addUser(dan);
        User maria = new User("Maria", 25, "London", "Lorem Ipsum", true, mariaAvailability);
        userService.addUser(maria);

        userService.addHobbyToUser(fiveAside.getId(), sunny.getId());
        userService.addHobbyToUser(fiveAside.getId(), dan.getId());
        userService.addHobbyToUser(fiveAside.getId(), maria.getId());

        Booking booking = new Booking("18:00", "11/06/2024", wembly, fiveAside);
        bookingRepository.save(booking);
        bookingService.addUserToBooking(sunny.getId(), booking.getId());
        bookingService.addUserToBooking(dan.getId(), booking.getId());
        bookingService.addUserToBooking(maria.getId(), booking.getId());
        bookingRepository.save(booking);
       }
}
