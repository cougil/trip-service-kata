package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TripDAOShould {

    public static final User NORMAL_USER = new User();
    public static final Trip BARCELONA = new Trip();

    @Test
    public void return_trips_from_user() {
        TripDAO tripDAO = new TripDAO();
        NORMAL_USER.addTrip(BARCELONA);
        List<Trip> tripList = tripDAO.findTripsBy(NORMAL_USER);
        assertThat(tripList.size(),equalTo(1));
    }

}