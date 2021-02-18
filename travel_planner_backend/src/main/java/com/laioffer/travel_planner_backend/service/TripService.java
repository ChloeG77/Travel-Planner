package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.dao.TripDao;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    private TripDao tripDao;

    public Trip getTripById(int tripId) {
        return tripDao.getTripById(tripId);
    }

    public void addTrip(Trip trip) {
        tripDao.addTrip(trip);
    }

    public void deleteTrip(int tripId) {
        tripDao.deleteTrip(tripId);
    }

    public void updateTrip(Trip trip) {
        tripDao.updateTrip(trip);

    }
    public void addPlace(int tripId, Place place) {
        tripDao.addPlace(tripId, place);
    }
    public void deletePlace(int tripId, int placeId) {
        tripDao.deletePlace(tripId, placeId);
    }

}
