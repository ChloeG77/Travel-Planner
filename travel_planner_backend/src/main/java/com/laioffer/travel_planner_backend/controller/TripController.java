package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Trip;
import com.laioffer.travel_planner_backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @RequestMapping(value = "trip/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public Trip getTripById(@PathVariable(value = "tripId") long tripId) {
        return tripService.getTripById(tripId);
    }


    @RequestMapping(value = "trip/days/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Day> getTripDays(@PathVariable(value = "tripId") int tripId) {
        return tripService.getTripById(tripId).getDays();
    }

    @RequestMapping(value = "trip/{tripId}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTrip(@PathVariable(value = "tripId") long tripId) {
        tripService.deleteTrip(tripId);
        return "redirect:/getAllTrip";
    }

    @RequestMapping(value = "trip/newTrip", method = RequestMethod.POST)
    @ResponseBody
    public String addTrip(@ModelAttribute Trip trip, BindingResult result) {
        if (result.hasErrors()) {
            return "addTrip";
        }
        tripService.addTrip(trip);
        return "redirect:/getAllTrip";
    }

    @RequestMapping(value = "trip/{tripId}/{placeId}", method = RequestMethod.POST)
    @ResponseBody
    public String addPlace(@PathVariable(value = "tripId") long tripId, @ModelAttribute Place place, BindingResult result) {
        if (result.hasErrors()) {
            return "addTrip";
        }
        tripService.addPlace(tripId, place);
        return "redirect:/getAllTrip";
    }


    @RequestMapping(value = "trip/{tripId}/{placeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePlace(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "placeId") String placeId) {
        tripService.deletePlace(tripId, placeId);
        return "redirect:/getAllTrip";
    }

    @RequestMapping(value = "trip/{tripId}", method = RequestMethod.POST)
    @ResponseBody
    public String editTrip(@ModelAttribute Trip trip, @PathVariable(value = "tripId") long tripId) {
        trip.setTripId(tripId);
        tripService.updateTrip(trip);
        return "redirect:/getAllTrip";
    }


    @RequestMapping(value = "trip/privacy/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean getTripPrivacy(@PathVariable(value = "tripId") long tripId) {

        return tripService.getTripById(tripId).isPrivate();
    }

    @RequestMapping(value = "trip/{privacy}/{tripId}", method = RequestMethod.POST)
    @ResponseBody
    public String setTripPrivacy(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "privacy") boolean privacy) {
        tripService.getTripById(tripId).setPrivate(privacy);
        return "redirect:/getAllTrip";
    }

    @RequestMapping(value = "trip/rating/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public double getTripRating(@PathVariable(value = "tripId") long tripId) {

        return tripService.getTripById(tripId).getRating();
    }

    @RequestMapping(value = "trip/{rating}/{tripId}", method = RequestMethod.POST)
    @ResponseBody
    public String setTripRating(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "rating") double rating) {

        return "redirect:/getAllTrip";
    }

}
