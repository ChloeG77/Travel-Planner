package com.laioffer.travel_planner_backend.message.response;

import com.laioffer.travel_planner_backend.entity.Trip;
import java.util.List;

public class TripsResponse {
    
    private List<Trip> trips;
    private Trip newTrip;
    public TripsResponse(List<Trip> trips, Trip newTrip) {
        this.trips = trips;
        this.newTrip = newTrip;
    }

    public Trip getNewTrip() {
        return newTrip;
    }

    public void setNewTrip(Trip newTrip) {
        this.newTrip = newTrip;
    }

    public List<Trip> getTrips() {
        return trips;
    }
    
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}