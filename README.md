# Booking Buddies

Booking Buddies, because your sofa has seen enough of you.

## Description

Booking Buddies is a RESTful API built using the Java Spring Boot framework and utilises PostgreSQL for database management. The idea behind our API is to connect users based around common hobbies. Allowing them to book activities based around those hobbies and get off that sofa! Using our API you can create a user profile, assign yourself a hobby such as basketball and either join or create a booking based around your hobby.

## Features

Here are a few of the features included within our API.

### MVP:

* Ability to create and manage a User entity:
    * Change name, age, location, manage hobbies, manage bookings.
* Create a Hobby.
* Create a Venue.
* Manage a Venue by:
    * Changing the name
    * Changing the location
    * Changing the capacity
* Assign a user a Hobby.
* Create a booking where users with similar hobbies can organise a time, date and venue.
* Manage a booking by:
    * Adding/removing users
    * Changing the date/time
    * Changing the venue
    * Changeing the hobby
* Utilises CRUD functionality to manage entities and their relationships.

### Extension

* Levenshtein Distance Algorithm to measure the difference between two strings relating to hobby name.
* Utilising the LocalDate datatype to manage the Date and Time of a booking.
* Managing the availability of a user using ENUMs and Zellers Congruence Algorithm to get a day of the week from any date.
* Ability to recommend bookings to other Users based on location and availability.
* Public and Private user profiles.
* Dynamic Venue Capacity.
* Custom Runtime Exceptions.

## Installation

1. **Clone the repository**:

   ```sh
   git clone git@github.com:marias52/week_7_backend_project.git
   cd week_7_backend_project
   ```

2. **Set up the PostgreSQL database**:

    Create a database in the terminal using the following command:

    ```sh
        createdb hobby_api
    ```

3. **Build and Run the Application in IntelliJ**

The API will then be available at **http://localhost:8080**

### Dependencies:

* JDK 17
* Spring Boot Web
* Spring Boot DevTools
* JPA
* PostgreSQL Driver

## References:

* [Zellers Congruence Algorithm](https://medium.com/@vp2005rawal/from-dates-to-days-unveiling-zellers-congruence-for-the-common-man-653ce71571a5)
* [Levenshtein Distance Algoithm] (https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/similarity/LevenshteinDistance.html)

## Contributors:

* [Maria Sharif](https://github.com/marias52/)
* [Danash Mahmood](https://github.com/Danash-Mahmood/)
* [Sunny Mudhar](https://github.com/sunnymudhar/)
