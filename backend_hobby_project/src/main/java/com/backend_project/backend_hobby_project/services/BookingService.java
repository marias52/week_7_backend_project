package com.backend_project.backend_hobby_project.services;

import com.backend_project.backend_hobby_project.models.*;
import com.backend_project.backend_hobby_project.repositories.BookingRepository;
import com.backend_project.backend_hobby_project.repositories.HobbyRepository;
import com.backend_project.backend_hobby_project.repositories.UserRepository;
import com.backend_project.backend_hobby_project.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    public List<Booking> findAllBookings(){
        return this.bookingRepository.findAll();
    }

    public Optional<Booking> findBookingById(long id){
        return this.bookingRepository.findById(id);
    }

    public void addBooking(Booking booking){
        this.bookingRepository.save(booking);
    }

    public void makeBooking(BookingDTO bookingDTO){

        Long hobbyId = bookingDTO.getHobbyId();
        Long venueId = bookingDTO.getVenueId();
        List<Long> userIds = bookingDTO.getUserIds();
        String date = bookingDTO.getDate();
        String time = bookingDTO.getTime();
        Venue venue = venueRepository.findById(venueId).get();
        Hobby hobby = hobbyRepository.findById(hobbyId).get();

        Booking booking = new Booking(time, date, venue, hobby);
        this.addBooking(booking);

        for(long id: userIds){
            User user = userRepository.findById(id).get();
            this.addUserToBooking(user, booking.getId());
        }
    }

    public void addUserToBooking (User user, Long id){
        Booking booking;
        booking = this.findBookingById(id).get();
        List<User> bookingUserList = booking.getUsers();
        bookingUserList.add(user);
        booking.setUsers(bookingUserList);
        this.addBooking(booking);
    }

    public void removeAllUserFromBooking (Long id){
        Booking booking = this.findBookingById(id).get();
        List<User> listOfUsers = booking.getUsers();
        listOfUsers.clear();
        booking.setUsers(listOfUsers);
        this.addBooking(booking);
    }

    public void deleteBooking (Long id){
        Booking booking = this.findBookingById(id).get();
        this.removeAllUserFromBooking(id);
        bookingRepository.delete(booking);
    }

    public void updateBooking (BookingDTO bookingDTO, Long id){
        Booking booking = this.findBookingById(id).get();
        Long hobbyId = bookingDTO.getHobbyId();
        Long venueId = bookingDTO.getVenueId();
        List<Long> userIds = bookingDTO.getUserIds();
        String date = bookingDTO.getDate();
        String time = bookingDTO.getTime();
        Venue venue = venueRepository.findById(venueId).get();
        Hobby hobby = hobbyRepository.findById(hobbyId).get();

        List<User> listToUpdate = booking.getUsers();

        for(Long uId: userIds){
            User user = userRepository.findById(uId).get();
            listToUpdate.add(user);
        }
        booking.setUsers(listToUpdate);
        booking.setDate(date);
        booking.setHobby(hobby);
        booking.setTime(time);
        booking.setVenue(venue);

        bookingRepository.save(booking);
    }

}
