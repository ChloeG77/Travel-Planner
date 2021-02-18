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

@Controller
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "place/{placeId}", method = RequestMethod.POST)
    public String addPlace(@ModelAttribute Place place, BindingResult result) {
        if (result.hasErrors()) {
            return "addPlace";
        }
        placeService.addPlace(place);
        return "redirect:/getAllPlace";
    }

    @RequestMapping(value = "place/{placeId}", method = RequestMethod.DELETE)
    public String deletePlace(@PathVariable(value = "placeId") String placeId) {
        placeService.deletePlace(placeId);
        return "redirect:/getAllPlace";
    }

    @RequestMapping(value = "place/{placeId}", method = RequestMethod.GET)
    public String searchPlaceById(@PathVariable(value = "placeId") String placeId) {
        placeService.searchPlaceById(placeId);
        // TODO: to complete and return actual place objects
        return "redirect:/getAllPlace";
    }

    @RequestMapping(value = "place/name/{name}", method = RequestMethod.GET)
    public String searchPlaceByName(@PathVariable(value = "name") String name) {
        placeService.searchPlaceByName(name);
        // TODO: to complete and return list of actual place objects
        return "redirect:/getAllPlace";
    }

}