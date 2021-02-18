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

    @RequestMapping(value = "place/addPlace", method = RequestMethod.POST)
    public String addPlace (@ModelAttribute Place place, BindingResult result) {
        if (result.hasErrors()) {
            return "addPlace";
        }
        placeService.addPlace(place);
        return "redirect:/getAllPlace";
    }


    @RequestMapping (value = "delete/{placeId}")
    public String deletePlace(@PathVariable(value = "placeId") int placeId) {
        placeService.deletePlace(placeId);
        return "redirect:/getAllPlace";
    }

}