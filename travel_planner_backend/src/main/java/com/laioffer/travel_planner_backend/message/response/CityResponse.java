package com.laioffer.travel_planner_backend.message.response;

import com.laioffer.travel_planner_backend.entity.City;

import java.util.List;

public class CityResponse {
    private List<City> cities;

    public CityResponse(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
