package com.laioffer.travel_planner_backend.service;

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
        place.updatePlace(place);
    }
}