package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripServiceShould {

    private static final User ANONYMOUS_USER = null;
    private static final User NORMAL_USER = aUser();
    private static final User FRIEND = aUser();
    private static final Trip BARCELONA = new Trip();
    private static final Trip LONDON = new Trip();

    private TripDAO tripDao = new TripDAO();
    private TripService tripService = new TripService(tripDao);

    @Test(expected = UserNotLoggedInException.class)
    public void thrown_an_exception_if_user_not_logged_in() {
        tripService.getTripsByUser(NORMAL_USER, ANONYMOUS_USER);
    }

    @Test
    public void return_no_trips_when_users_are_not_friends() {
        assertThat(tripService.getTripsByUser(FRIEND, NORMAL_USER).size(),is(0));
    }

    @Test
    public void return_user_trips_when_users_are_friends() {
        User user = new UserBuilder()
                .addFriends(FRIEND)
                .addTrips(BARCELONA, LONDON)
                .build();
        List<Trip> tripsList = tripService.getTripsByUser(user, FRIEND);
        assertThat(tripsList,equalTo(user.trips()));
        assertThat(tripsList.size(),equalTo(2));
    }

    private static User aUser() {
        return new UserBuilder().build();
    }

}