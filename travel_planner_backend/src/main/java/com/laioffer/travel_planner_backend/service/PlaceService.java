package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.entity.City;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.external.GoogleMapClient;
import com.laioffer.travel_planner_backend.repository.CityRepository;
import com.laioffer.travel_planner_backend.repository.PlaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaceService {
    
    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private GoogleMapClient client;
    
    @Transactional
    public void addPlace(Place place) {
        placeRepository.save(place);
    }
    
    @Transactional
    public void deletePlace(String placeId) {
        Place place = getPlaceById(placeId);
        placeRepository.delete(place);
    }
    
    @Transactional
    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }
    
    @Transactional
    public Place getPlaceById(String placeId) {
        return placeRepository.findById(placeId).orElseThrow(() ->
            new ItemNotFoundException("Place Not Found with -> PlaceId : " + placeId)
        );
    }
    
    @Transactional
    public Place searchPlaceById(String placeId) {
        return client.searchByID(placeId);
    }
    
    @Transactional
    public List<Place> searchPlaceByName(String name, String city) {
        return client.searchByName(name, city);
    }
    
    @Transactional
    public List<Place> searchNearby(String locStr) {
        int nearbyRad = 3000;
        String type = "point_of_interest";
        return client.searchNearby(locStr, nearbyRad, type);
    }
    
    @Transactional
    public Place addPlace(String placeId) {
        try {
            // Check if place already in place table
            return getPlaceById(placeId);
        } catch (ItemNotFoundException e) {
            // If not, search using google map api
            return addCity(searchPlaceById(placeId));
        }
    }
    
    @Transactional
    public Place addCity(Place place) {
        String cityName = place.getCityName();
        String state = place.getState();
        String country = place.getCountry();
        List<City> cities = cityRepository.findByNameAndStateAndCountry(cityName, state, country);
        City city;
        if (cities.size() == 0) {
            city = new City();
            city.setName(cityName);
            city.setState(state);
            city.setCountry(country);
        } else {
            city = cities.get(0);
        }
        
        cityRepository.save(city);
        place.setCity(city);
        placeRepository.save(place);
        return place;
    }
}