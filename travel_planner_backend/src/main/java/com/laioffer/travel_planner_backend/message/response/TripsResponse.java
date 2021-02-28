package com.laioffer.travel_planner_backend.message.response;

import com.laioffer.travel_planner_backend.entity.Trip;
import java.util.List;

public class TripsResponse {
    
    private List<Trip> trips;
    
    public TripsResponse(List<Trip> trips) {
        this.trips = trips;
    }
    
    public List<Trip> getTrips() {
        return trips;
    }
    
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}