package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("place/{placeId}")
    public String addPlace(@PathVariable(value = "placeId") long placeId, @RequestBody Place place,
            BindingResult result) {
        if (result.hasErrors()) {
            return "addPlace error";
        }
        placeService.addPlace(place);
        return "redirect:/getAllPlace";

    }

    // @RequestMapping(value = "place/{placeId}", method = RequestMethod.POST)
    // public String addPlace(@PathVariable String placeId) throws IOException {
    // Place place = placeService.searchPlaceById(placeId);
    // placeService.addPlace(place);
    // placeService.addCity(place);
    // return "redirect:/getAllPlace";
    // }

    @DeleteMapping("place/{placeId}")
    public String deletePlace(@PathVariable(value = "placeId") long palceId,
            @PathVariable(value = "placeId") String placeId) {
        placeService.deletePlace(placeId);
        return "redirect:/ getAllPlace";

    }
    // @RequestMapping(value = "place/{placeId}", method = RequestMethod.DELETE)
    // public String deletePlace(@PathVariable(value = "placeId") String placeId) {
    // placeService.deletePlace(placeId);
    // return "redirect:/getAllPlace";
    // }

    @GetMapping("place/{placeId}")
    public Place searchPlaceById(@PathVariable(value = "placeId") String placeId) throws IOException {
        return placeService.searchPlaceById(placeId);
    }
    // @RequestMapping(value = "place/{placeId}", method = RequestMethod.GET)
    // public Place searchPlaceById(@PathVariable(value = "placeId") String placeId)
    // throws IOException {
    // return placeService.searchPlaceById(placeId);
    // }

    @GetMapping("place/{city}/{name}")
    public List<Place> searchPlaceByName(@PathVariable(value = "name") String name, @PathVariable String city)
            throws IOException, InterruptedException {
        List<String> allPlaceId = placeService.searchPlaceByName(name, city);
        List<Place> res = new ArrayList<>();
        for (String id : allPlaceId) {
            res.add(placeService.searchPlaceById(id));
        }
        return res;
    }
    // @RequestMapping(value = "place/{city}/{name}", method = RequestMethod.GET)
    // public List<Place> searchPlaceByName(@PathVariable(value = "name") String
    // name, @PathVariable String city) throws IOException, InterruptedException {
    // List<String> allPlaceId = placeService.searchPlaceByName(name, city);
    // List<Place> res = new ArrayList<>();
    // for (String id: allPlaceId) {
    // res.add(placeService.searchPlaceById(id));
    // }
    // return res;
    // }
}