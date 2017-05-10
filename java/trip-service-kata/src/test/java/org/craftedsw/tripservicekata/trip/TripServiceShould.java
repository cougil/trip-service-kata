package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceShould {

    @Test(expected = UserNotLoggedInException.class)
    public void thrown_an_exception_if_user_not_logged_in() {
        TripService tripService = new TripServiceTest();
        User user = new User();
        tripService.getTripsByUser(user);
    }

    private class TripServiceTest extends TripService {
        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}