##Methods Explanation

The following tables summarize the methods in the BookingService, HobbyService, UserService, and VenueService classes. 

###BookingService Methods:

**Method**|**Description**
:-----:|:-----:
makeBooking|Creates a new booking using the BookingDTO, checks venue capacity, and assigns users. Returns the created Booking object.
addUserToBooking|Adds a user to a booking by their ID, ensuring they're not already added and adjusts venue capacity.
removerUserFromBooking|Removes a user from a booking by their ID and updates venue capacity.
removeAllUserFromBooking|Removes all users from a booking and updates venue capacity.
removeUserFromAllBookings|Removes a user from all their bookings.
removeHobbyFromBooking|Removes the hobby from a booking by ID.
removeHobbyFromAllBookings|Removes a hobby from all bookings where it's assigned.
removeVenueFromBooking|Removes the venue from a booking by ID.
removeVenueFromAllBookings|Removes a venue from all bookings where it's assigned.
updateBooking|Updates a booking with new details using the BookingDTO and booking ID.
updateBookingProp|Updates a specific property (time, date, hobby, venue, users) of a booking.
recommendBookings|Suggests bookings to a user based on location and availability.
zellersCongruence|Calculates the day of the week for a given date using Zeller's Congruence Algorithm.
convZellersToDay|Converts a date to a specific day and time period (morning, afternoon, evening).


###HobbyService Methods:

**Method**|**Description**
:-----:|:-----:
updateHobby|Updates the name of a hobby using the HobbyDTO and hobby ID.
checkForCloseSpelling|Uses the Levenshtein Distance Algorithm to check for similar spellings between two strings. Returns true if similarity is above 0.8.
addHobby|Adds a new hobby if it doesn't already exist or have a similar name. Returns the new hobby or null if not added.


###UserService Methods:

**Method**|**Description**
:-----:|:-----:
getUserBookings|Retrieves all bookings for a user by their ID.
deleteUserById|Deletes a user by their ID.
setUserName|Sets the name of a user by their ID.
setUserAge|Sets the age of a user by their ID.
setUserLocation|Sets the location of a user by their ID.
setUserBiography|Sets the biography of a user by their ID.
setUserPrivacy|Sets the privacy status of a user by their ID.
setUserAvailability|Sets the availability of a user by their ID.
addHobbyToUser|Adds a hobby to a user by their ID and the hobby ID.
removeHobbyFromUser|Removes a hobby from a user by their ID and the hobby ID.
updateUser|Updates a user using the UserDTO and user ID.
updateUserProp|Updates a specific property (name, age, location, biography, privacy, availability, hobbies) of a user.
getPrivateUsers|Retrieves all users marked as private.
getPublicUsers|Retrieves all users marked as public.
getUserAvailabilityById|Retrieves the availability of a user by their ID.

###VenueService Methods:

**Method**|**Description**
:-----:|:-----:
findAllVenues|Retrieves all venues.
findVenueById|Finds a venue by its ID.
addVenue|Adds a new venue to the repository.
deleteVenueById|Deletes a venue by its ID.
deleteVenue|Deletes a venue entity.
updateVenue|Updates a venue using the VenueDTO and venue ID.
updateVenueProp|Updates a specific property (name, location, capacity) of a venue.














