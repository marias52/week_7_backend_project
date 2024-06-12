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

    public Booking makeBooking(BookingDTO bookingDTO){

        Long hobbyId = bookingDTO.getHobbyId();
        Long venueId = bookingDTO.getVenueId();
        List<Long> userIds = bookingDTO.getUserIds();
        String date = bookingDTO.getDate();
        String time = bookingDTO.getTime();
        Venue venue = venueRepository.findById(venueId).get();
        Hobby hobby = hobbyRepository.findById(hobbyId).get();

        Booking booking = new Booking(time, date, venue, hobby);
        this.addBooking(booking);
        Long bookingId = booking.getId();

        for(long id: userIds){

            this.addUserToBooking(id, bookingId);
        }

        return booking;
    }

    public void addUserToBooking (Long userId, Long bookingId){
        Booking booking = this.findBookingById(bookingId).get();
        User user = userRepository.findById(userId).get();
        List<User> bookingUserList = booking.getUsers();
        bookingUserList.add(user);
        booking.setUsers(bookingUserList);
        this.addBooking(booking);
    }

    public void removerUserFromBooking(Long userId, Long bookingId){
        Booking booking = this.findBookingById(bookingId).get();
        User user = userRepository.findById(userId).get();
        List<User> bookingUserList = booking.getUsers();
        bookingUserList.remove(user);
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

    public void removeHobbyFromBooking(long id){
        Booking booking = this.bookingRepository.findById(id).get();
        booking.setHobby(null);
        this.addBooking(booking);
    }

    public void removeHobbyFromAllBookings(long hobbyId){
        for(Booking booking : this.findAllBookings()){
            if(booking.getHobby() == hobbyRepository.findById(hobbyId).get()){
                this.removeHobbyFromBooking(hobbyId);
            }
        }
    }

    public void removeVenueFromBooking(long id){
        Booking booking = this.bookingRepository.findById(id).get();
        booking.setVenue(null);
        this.addBooking(booking);
    }

    public void removeVenueFromAllBookings(long venueId){
        for(Booking booking : this.findAllBookings()){
            if(booking.getVenue() == venueRepository.findById(venueId).get()){
                this.removeVenueFromBooking(venueId);
            }
        }
    }

    public void setTimeForBooking(String time, Long bookingId) {
        Booking booking = this.findBookingById(bookingId).get();
        booking.setTime(time);
        bookingRepository.save(booking);
    }

    public void setDateForBooking(String date, Long bookingId){
        Booking booking = this.findBookingById(bookingId).get();
        booking.setDate(date);
        bookingRepository.save(booking);
    }

    public void setHobbyForBooking(Long hobbyId,Long bookingId){
        Hobby hobby = this.hobbyRepository.findById(hobbyId).get();
        Booking booking = this.findBookingById(bookingId).get();
        booking.setHobby(hobby);
        bookingRepository.save(booking);
    }

    public void setVenueForBooking(Long venueId, Long bookingId){
        Venue venue = this.venueRepository.findById(venueId).get();
        Booking booking = this.findBookingById(bookingId).get();
        booking.setVenue(venue);
        bookingRepository.save(booking);

    }

    public void deleteBooking(Long id){
        Booking booking = this.findBookingById(id).get();
        this.removeAllUserFromBooking(id);
        this.removeHobbyFromBooking(id);
        this.removeVenueFromBooking(id);
        bookingRepository.delete(booking);
    }

    public Booking updateBooking (BookingDTO bookingDTO, Long id){
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

        return booking;
    }

    public Booking updateBookingProp (BookingDTO bookingDTO, long bookingId, String property) {
        switch (property) {
            case "time":
                setTimeForBooking(bookingDTO.getTime(), bookingId);
                break;
            case "date":
                setDateForBooking(bookingDTO.getDate(),bookingId);
                break;
            case "hobby":
                setHobbyForBooking(bookingDTO.getHobbyId(),bookingId);
                break;
            case "venue":
                setVenueForBooking(bookingDTO.getVenueId(),bookingId);
                break;
            case "addUsers":
                for(Long userID: bookingDTO.getUserIds()){
                    this.addUserToBooking(userID, bookingId);
                }
                break;
            case "removeUsers":
                for(Long userID: bookingDTO.getUserIds()) {
                    this.removerUserFromBooking(userID, bookingId);
                }
                break;
            default:
                break;
        }

        return this.findBookingById(bookingId).get();
    }

}
