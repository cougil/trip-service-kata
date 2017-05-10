package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripServiceShould {

    public static final User ANONYMOUS_USER = null;
    public static final User NORMAL_USER = new User();
    public static final User FRIEND = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void thrown_an_exception_if_user_not_logged_in() {
        TripService tripServiceTest = new TripServiceTest(ANONYMOUS_USER);
        tripServiceTest.getTripsByUser(NORMAL_USER);
    }

    @Test
    public void return_empty_list_when_users_are_not_friends() {
        TripService tripServiceTest = new TripServiceTest(NORMAL_USER);
        assertThat(tripServiceTest.getTripsByUser(FRIEND).size(),is(0));
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