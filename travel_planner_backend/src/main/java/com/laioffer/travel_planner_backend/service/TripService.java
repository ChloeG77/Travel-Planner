package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.dao.TripDao;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripDao tripDao;

    public Trip getTripById(long tripId) {
        return tripDao.getTripById(tripId);
    }

    public void addTrip(Trip trip) {
        tripDao.addTrip(trip);
    }

    public void deleteTrip(long tripId) {
        tripDao.deleteTrip(tripId);
    }

    public void updateTrip(Trip trip) {
        tripDao.updateTrip(trip);
    }

    public void addPlace(long tripId, Place place) {
        tripDao.addPlace(tripId, place);
    }

    public void deletePlace(long tripId, String placeId) {
        tripDao.deletePlace(tripId, placeId);
    }

}
