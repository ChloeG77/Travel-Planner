package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.repository.PlaceRepository;
import com.laioffer.travel_planner_backend.dao.CityDao;
import com.laioffer.travel_planner_backend.dao.PlaceDao;
import com.laioffer.travel_planner_backend.entity.City;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.external.GoogleMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CityDao cityDao;

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
    public void updatePlace(Place place) {
        placeRepository.save(place);
    }

    @Transactional
    public Place getPlaceById(String placeId) {
        Place place = getPlaceById(placeId);
        return place;
    }

    @Transactional
    public Place searchPlaceById(String placeId) throws IOException {
        return client.searchByID(placeId);
    }

    @Transactional
    public List<String> searchPlaceByName(String name, String city) throws IOException, InterruptedException {
        return client.searchByName(name, city);
    }

    @Transactional
    public Place addCity(Place place) {
        String cityName = place.getCityName();
        String state = place.getState();
        String country = place.getCountry();
        City city = cityDao.getCity(cityName, state, country);
        place.setCity(city);
        placeRepository.save(place);
        return place;
    }
}