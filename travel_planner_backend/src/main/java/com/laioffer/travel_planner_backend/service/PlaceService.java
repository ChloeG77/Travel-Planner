package com.laioffer.travel_planner_backend.service;


import com.laioffer.travel_planner_backend.dao.PlaceDao;
import com.laioffer.travel_planner_backend.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    @Autowired
    private PlaceDao placeDao;

    public void addPlace (Place place) {
        placeDao.addPlace(place);
    }

    public void deletePlace (int placeId) {
        placeDao.deletePlace(placeId);
    }

    public void updatePlace(Place place) {
        placeDao.updatePlace(place);
    }
}