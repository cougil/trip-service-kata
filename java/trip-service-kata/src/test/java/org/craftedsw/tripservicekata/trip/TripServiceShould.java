package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripServiceShould {

    @Test(expected = UserNotLoggedInException.class)
    public void thrown_an_exception_if_user_not_logged_in() {
        User user = new User();
        User loggedUser = null;
        TripService tripServiceTest = new TripServiceTest(loggedUser);
        tripServiceTest.getTripsByUser(user);
    }

    @Test
    public void return_empty_list_when_users_are_not_friends() {
        User user = new User();
        TripService tripServiceTest = new TripServiceTest(user);
        User friend = new User();
        assertThat(tripServiceTest.getTripsByUser(user).size(),is(0));
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
    }
}