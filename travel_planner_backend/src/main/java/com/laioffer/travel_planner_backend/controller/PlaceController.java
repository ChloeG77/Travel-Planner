package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.City;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.service.PlaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlaceController {
    
    @Autowired
    private PlaceService placeService;
    
    @PostMapping("place")
    public Place addPlace(@RequestParam String placeId) {
        return placeService.addPlace(placeId);
    }
    
    @DeleteMapping("place")
    public String deletePlace(@RequestParam String placeId) {
        placeService.deletePlace(placeId);
        return "redirect:/ getAllPlace";
    }
    
    @GetMapping("place/searchById")
    public Place searchPlaceById(@RequestParam String placeId) {
        return placeService.searchPlaceById(placeId);
    }
    
    @GetMapping("place/searchByName")
    public List<Place> searchPlaceByName(@RequestParam String text,
        @RequestParam String city) {
        return placeService.searchPlaceByName(text, city);
    }
    
    @GetMapping(value = "place/recommendation", params = {"city"})
    public List<Place> getRecCity(@RequestParam String city) {
        return placeService.searchPlaceByName("tourist attraction", city);
    }
    
    @GetMapping(value = "place/recommendation", params = {"loc"})
    public List<Place> getRecNearby(@RequestParam String loc) {
        return placeService.searchNearby(loc);
    }
    
    @GetMapping("city/getAllCities")
    public List<City> getAllCities() {
        return placeService.getAllCities();
    }
    
    @GetMapping("city/getCity")
    public City getCityById(@RequestParam long cityId) {
        return placeService.getCityById(cityId);
    }
}