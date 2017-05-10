package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private final TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        if (loggedUser != null) {
            return user.hasFriend(loggedUser) ? findTripsBy(user) : noTrips();
        } else {
            throw new UserNotLoggedInException();
        }
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    protected List<Trip> findTripsBy(User user) {
        return tripDAO.findTripsBy(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
