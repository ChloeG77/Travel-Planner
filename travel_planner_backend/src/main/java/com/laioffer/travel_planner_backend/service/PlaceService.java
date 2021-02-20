package com.laioffer.travel_planner_backend.service;


import com.laioffer.travel_planner_backend.dao.CityDao;
import com.laioffer.travel_planner_backend.dao.PlaceDao;
import com.laioffer.travel_planner_backend.entity.City;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.external.GoogleMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private GoogleMapClient client;

    public void addPlace(Place place) {
        placeDao.addPlace(place);
    }

    public void deletePlace(String placeId) {
        placeDao.deletePlace(placeId);
    }

    public void updatePlace(Place place) {
        placeDao.updatePlace(place);
    }

    public Place getPlaceById(String placeId) {
        return placeDao.getPlaceById(placeId);
    }

    public Place searchPlaceById(String placeId) throws IOException {
        return client.searchByID(placeId);
    }

    public List<String> searchPlaceByName(String name, String city) throws IOException, InterruptedException {
        return client.searchByName(name, city);
    }

    public Place addCity(Place place) {
        String cityName = place.getCityName();
        String state = place.getState();
        String country = place.getCountry();
        City city = cityDao.getCity(cityName, state, country);
        place.setCity(city);
        placeDao.updatePlace(place);
        return place;
    }
}