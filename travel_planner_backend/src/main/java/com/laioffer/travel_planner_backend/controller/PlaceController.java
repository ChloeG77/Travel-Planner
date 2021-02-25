package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.service.ItemNotFoundException;
import com.laioffer.travel_planner_backend.service.PlaceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlaceController {
    
    @Autowired
    private PlaceService placeService;
    
    @PostMapping("place/{placeId}")
    public Place addPlace(@PathVariable(value = "placeId") String placeId) {
        Place place;
        try {
            // Check if place already in place table
            place = placeService.getPlaceById(placeId);
        } catch (ItemNotFoundException e) {
            // If not, search using google map api
            place = placeService.searchPlaceById(placeId);
            // placeService.addPlace(place);
            placeService.addCity(place);
        }
        return place;
    }
    
    @DeleteMapping("place/{placeId}")
    public String deletePlace(@PathVariable(value = "placeId") String placeId) {
        placeService.deletePlace(placeId);
        return "redirect:/ getAllPlace";
    }
    
    @GetMapping("place/{placeId}")
    public Place searchPlaceById(@PathVariable(value = "placeId") String placeId) {
        return placeService.searchPlaceById(placeId);
    }
    
    
    @GetMapping("place/{city}/{name}")
    public List<Place> searchPlaceByName(@PathVariable(value = "name") String name,
        @PathVariable String city) {
        System.out.println(city);
        System.out.println(name);
        List<String> allPlaceId = placeService.searchPlaceByName(name, city);
        List<Place> res = new ArrayList<>();
        for (String id : allPlaceId) {
            res.add(placeService.searchPlaceById(id));
        }
        return res;
    }
}