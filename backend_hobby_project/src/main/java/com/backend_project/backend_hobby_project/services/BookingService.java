package com.backend_project.backend_hobby_project.services;

import com.backend_project.backend_hobby_project.enums.DaysOfTheWeek;
import com.backend_project.backend_hobby_project.models.*;
import com.backend_project.backend_hobby_project.repositories.BookingRepository;
import com.backend_project.backend_hobby_project.repositories.HobbyRepository;
import com.backend_project.backend_hobby_project.repositories.UserRepository;
import com.backend_project.backend_hobby_project.repositories.VenueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
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

    public String convertLocalDateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public String convertLocalTimeToString(LocalTime time){
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatTime);
    }







    public Booking makeBooking(BookingDTO bookingDTO){

        Long hobbyId = bookingDTO.getHobbyId();
        Long venueId = bookingDTO.getVenueId();
        Venue venue = venueRepository.findById(venueId).get();
        Hobby hobby = hobbyRepository.findById(hobbyId).get();
        List<Long> userIds = bookingDTO.getUserIds();

        if(venue.getCapacity() - userIds.size() < 0) {
            return null;
        }

        LocalDate date = bookingDTO.getDate();
        String dateAsString = this.convertLocalDateToString(date);
        LocalTime time = bookingDTO.getTime();
        String timeAsString = this.convertLocalTimeToString(time);


        //format the local date back into a string to make the new booking object


        Booking booking = new Booking(timeAsString, dateAsString, venue, hobby);
        this.addBooking(booking);
        Long bookingId = booking.getId();

        for(long id: userIds){
            this.addUserToBooking(id, bookingId);
        }

        return booking;
    }

    @Transactional
    public void addUserToBooking (Long userId, Long bookingId){
        Booking booking = this.findBookingById(bookingId).get();
        Venue venue = booking.getVenue();
        User user = userRepository.findById(userId).get();
        List<User> bookingUserList = booking.getUsers();

        if(bookingUserList.contains(user)) {
            return;
        }

        bookingUserList.add(user);
        booking.setUsers(bookingUserList);
        this.addBooking(booking);
        venue.reduceCapacity();
        venueRepository.save(venue);
    }

    public void removerUserFromBooking(Long userId, Long bookingId){
        Booking booking = this.findBookingById(bookingId).get();
        User user = userRepository.findById(userId).get();

        if(!booking.getUsers().contains(user)) {
            return;
        }

        Venue venue = booking.getVenue();
        List<User> bookingUserList = booking.getUsers();
        bookingUserList.remove(user);
        venue.increaseCapacity();
        venueRepository.save(venue);
        booking.setUsers(bookingUserList);
        this.addBooking(booking);
    }

    public void removeAllUserFromBooking (Long id){
        Booking booking = this.findBookingById(id).get();
        List<User> listOfUsers = booking.getUsers();
        Venue venue = booking.getVenue();
        venue.setCapacity(venue.getCapacity() + listOfUsers.size());
        venueRepository.save(venue);
        listOfUsers.clear();
        booking.setUsers(listOfUsers);
        this.addBooking(booking);
    }

    public void removeUserFromAllBookings(Long userId) {
        for(Booking booking : this.bookingRepository.findAll()) {
            this.removerUserFromBooking(userId, booking.getId());
        }
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

    public Long deleteBooking(Long id){
        Booking booking = this.findBookingById(id).get();
        this.removeAllUserFromBooking(id);
        this.removeHobbyFromBooking(id);
        this.removeVenueFromBooking(id);
        bookingRepository.delete(booking);

        return id;
    }

    public Booking updateBooking (BookingDTO bookingDTO, Long id){
        Booking booking = this.findBookingById(id).get();
        Long hobbyId = bookingDTO.getHobbyId();
        Long venueId = bookingDTO.getVenueId();
        List<Long> userIds = bookingDTO.getUserIds();
        LocalDate date = bookingDTO.getDate();
        String dateAsString = this.convertLocalDateToString(date);
        LocalTime time = bookingDTO.getTime();
        String timeAsString = this.convertLocalTimeToString(time);

        Venue venue = venueRepository.findById(venueId).get();
        Hobby hobby = hobbyRepository.findById(hobbyId).get();

        List<User> listToUpdate = booking.getUsers();
        venue.setCapacity(venue.getCapacity() + listToUpdate.size());
        venueRepository.save(venue);
        listToUpdate.clear();

        for(Long uId: userIds){
            this.addUserToBooking(uId, booking.getId());
        }

        booking.setDate(dateAsString);
        booking.setHobby(hobby);
        booking.setTime(timeAsString);
        booking.setVenue(venue);

        bookingRepository.save(booking);

        return booking;
    }

    public Booking updateBookingProp (BookingDTO bookingDTO, long bookingId, String property) {
        switch (property) {
            case "time":
                LocalTime newTime = bookingDTO.getTime();
                String timeAsString = this.convertLocalTimeToString(newTime);
                setTimeForBooking(timeAsString, bookingId);
                break;
            case "date":
                LocalDate newDate = bookingDTO.getDate();
                String dateAsString = this.convertLocalDateToString(newDate);
                setDateForBooking(dateAsString,bookingId);
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

    public List<Booking> recommendBookings(UserDTO userDTO){

        List<Booking> recommendations = new ArrayList<>();
        String userLocation = userDTO.getLocation();
        for (Booking booking: this.findAllBookings()){

            LocalTime time = booking.getTime();
            Venue venue = booking.getVenue();

            int zellersInt = this.zellersCongruence(booking.getDate());
            DaysOfTheWeek day = convZellersToDay(zellersInt,time);
            for (DaysOfTheWeek userAvailability : userDTO.getAvailability()){
                if (userAvailability == day && venue.getLocation().equals(userLocation)){
                    recommendations.add(booking);
                }
            }
        }
        return recommendations;

    }
    public int zellersCongruence(LocalDate date){
        int numOfMonth = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        if (month == 1 ){
            month = 13;
            year--;
        }

        if (month == 2){
            month = 14;
            year--;
        }
        //this algorithm counts January and February as month 13 and 14 of the previous year

        int yearOfCentury = year%100;

        int century = year/100; //zero based century not typical century enumeration

        int dayOfTheWeek = (numOfMonth + (13*(month + 1))/5 + yearOfCentury + (yearOfCentury/4) + (century/4) - 2*century)%7;

        int convToStandard = (dayOfTheWeek +7)%7; //to get non negative result

        return dayOfTheWeek;


    }


    public DaysOfTheWeek convZellersToDay(int zellersInt, LocalTime time){
        int hour = time.getHour();

        if (zellersInt == 0 && hour >= 6 && hour < 12 ){
            return DaysOfTheWeek.SATURDAYMORNING;
        }
        else if (zellersInt==0 && hour>=12 && hour < 18){
            return DaysOfTheWeek.SATURDAYAFTERNOON;
        }
        else if (zellersInt==0 && hour>=18 && hour < 24){
            return DaysOfTheWeek.SATURDAYEVENING;
        }
        else if (zellersInt==1 && hour>=6 && hour < 12){
            return DaysOfTheWeek.SUNDAYMORNING;
        }
        else if (zellersInt==1 && hour>=12 && hour < 18){
            return DaysOfTheWeek.SUNDAYAFTERNOON;
        }
        else if (zellersInt==1 && hour>=18 && hour < 24){
            return DaysOfTheWeek.SUNDAYEVENING;
        }
        else if (zellersInt==2 && hour>=6 && hour < 12){
            return DaysOfTheWeek.MONDAYMORNING;
        }
        else if (zellersInt==2 && hour>=12 && hour < 18){
            return DaysOfTheWeek.MONDAYAFTERNOON;
        }
        else if (zellersInt==2 && hour>=18 && hour < 24){
            return DaysOfTheWeek.MONDAYEVENING;
        }
        else if (zellersInt==3 && hour>=6 && hour < 12){
            return DaysOfTheWeek.TUESDAYMORNING;
        }
        else if (zellersInt==3 && hour>=12 && hour < 18){
            return DaysOfTheWeek.TUESDAYAFTERNOON;
        }
        else if (zellersInt==3 && hour>=18 && hour < 24){
            return DaysOfTheWeek.TUESDAYEVENING;
        }
        else if (zellersInt==4 && hour>=6 && hour < 12){
            return DaysOfTheWeek.WEDNESDAYMORNING;
        }
        else if (zellersInt==4 && hour>=12 && hour < 18){
            return DaysOfTheWeek.WEDNESDAYAFTERNOON;
        }
        else if (zellersInt==4 && hour>=18 && hour < 24){
            return DaysOfTheWeek.WEDNESDAYEVENING;
        }
        else if (zellersInt==5 && hour>=6 && hour < 12){
            return DaysOfTheWeek.THURSDAYMORNING;
        }
        else if (zellersInt==5 && hour>=12 && hour < 18){
            return DaysOfTheWeek.THURSDAYAFTERNOON;
        }
        else if (zellersInt==5 && hour>=18 && hour < 24){
            return DaysOfTheWeek.THURSDAYEVENING;
        }
        else if (zellersInt==6 && hour>=6 && hour < 12){
            return DaysOfTheWeek.FRIDAYMORNING;
        }
        else if (zellersInt==6 && hour>=12 && hour < 18){
            return DaysOfTheWeek.FRIDAYAFTERNOON;
        }
        else if (zellersInt==6 && hour>=18 && hour < 24){
            return DaysOfTheWeek.FRIDAYEVENING;
        }
        return null;
    }


}
