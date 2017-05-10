package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

public class TripDAO {

	@Deprecated
	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}

	public List<Trip> findTripsBy(User user) {
		return user.trips();
	}

}