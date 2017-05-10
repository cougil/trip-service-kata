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
    private static final User NORMAL_USER = new User();
    private static final User FRIEND = new User();
    private static final Trip BARCELONA = new Trip();
    private static final Trip LONDON = new Trip();

    @Test(expected = UserNotLoggedInException.class)
    public void thrown_an_exception_if_user_not_logged_in() {
        TripService tripServiceTest = new TripServiceTest(ANONYMOUS_USER);
        tripServiceTest.getTripsByUser(NORMAL_USER);
    }

    @Test
    public void return_no_trips_when_users_are_not_friends() {
        TripService tripServiceTest = new TripServiceTest(NORMAL_USER);
        assertThat(tripServiceTest.getTripsByUser(FRIEND).size(),is(0));
    }

    @Test
    public void return_user_trips_when_users_are_friends() {
        NORMAL_USER.addTrip(BARCELONA);
        NORMAL_USER.addTrip(LONDON);
        NORMAL_USER.addFriend(FRIEND);
        TripService tripServiceTest = new TripServiceTest(FRIEND);
        List<Trip> tripsList = tripServiceTest.getTripsByUser(NORMAL_USER);
        assertThat(tripsList,equalTo(NORMAL_USER.trips()));
        assertThat(tripsList.size(),equalTo(2));
    }

    private class TripServiceTest extends TripService {
        private final User loggedUser;

        public TripServiceTest(User loggedUser) {
            this.loggedUser = loggedUser;
        }

        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> findTripsBy(User user) {
            return user.trips();
        }
    }
}