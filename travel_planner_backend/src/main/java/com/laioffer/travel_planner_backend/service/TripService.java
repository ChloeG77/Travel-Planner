package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Trip;
import com.laioffer.travel_planner_backend.repository.TripRepository;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TripService {
    
    @Autowired
    private TripRepository tripRepository;
    
    @Autowired
    private DayService dayService;
    
    @Transactional
    public Trip getTripById(long tripId) {
        return tripRepository.findById(tripId);
    }
    
    @Transactional
    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }
    
    public List<Trip> getTripsByUsername(String username) {
        return tripRepository.findAllByUser_Username(username);
        
    }
    
    @Transactional
    public void deleteTrip(long tripId) {
        Trip trip = getTripById(tripId);
        tripRepository.delete(trip);
        
    }
    
    @Transactional
    public void deleteTrip(String username, String tripName) {
        Trip trip = getTripByTripName(username, tripName);
        tripRepository.delete(trip);
    }
    
    @Transactional
    public Trip getTripByTripName(String username, String tripName) {
        return tripRepository.findByUser_UsernameAndName(username, tripName);
    }
    
    
    @Transactional
    public void updateTrip(Trip trip) {
        tripRepository.save(trip);
    }
    
    @Transactional
    public void addPlace(long tripId, Place place) {
        Trip trip = getTripById(tripId);
        trip.addPlace(place);
        tripRepository.save(trip);
    }
    
    @Transactional
    public void deletePlace(long tripId, String placeId) {
        Trip trip = getTripById(tripId);
        Place place = getPlace(tripId, placeId);
        trip.deletePlace(place);
        for (Day day : trip.getDays()) {
            dayService.deletePlace(day, place);
        }
        tripRepository.save(trip);
    }
    
    @Transactional
    public Place getPlace(long tripId, String placeId) {
        Trip trip = getTripById(tripId);
        Set<Place> places = trip.getPlaces();
        for (Place place : places) {
            if (place.getPlaceId().equals(placeId)) {
                return place;
            }
        }
        return null;
    }
    
    @Transactional
    public Set<Place> getAllPlaces(long tripId) {
        Trip trip = getTripById(tripId);
        trip.getCities();
        Set<Place> places = trip.getPlaces();
        return places;
    }
    
}
