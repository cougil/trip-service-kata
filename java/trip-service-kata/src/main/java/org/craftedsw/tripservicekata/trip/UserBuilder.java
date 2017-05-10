package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

public class UserBuilder {
    private Trip[] trips = new Trip[]{};
    private User[] friends = new User[]{};

    public User build() {
        User user = new User();
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
        for (User friend : friends) {
            user.addFriend(friend);
        }
        return user;

    }

    public UserBuilder addTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public UserBuilder addFriends(User... friends) {
        this.friends = friends;
        return this;
    }
}
