package com.laioffer.travel_planner_backend.message.response;

import com.laioffer.travel_planner_backend.entity.Place;

import java.util.Set;

public class PlaceResponse {
    private Set<Place> places;

    public PlaceResponse(Set<Place> places) {
        this.places = places;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
