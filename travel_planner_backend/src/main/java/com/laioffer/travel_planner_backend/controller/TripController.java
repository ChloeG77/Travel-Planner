package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Trip;
import com.laioffer.travel_planner_backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @RequestMapping(value="trip/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public Trip getTripId(@PathVariable(value="tripId") int tripId) {
        return tripService.getTripById(tripId);
    }


    @RequestMapping(value="trip/days/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public Trip getTripDays(@PathVariable(value="tripId") int tripId) {
        return tripService.getTripById(tripId);
    }

    @RequestMapping(value="trip/{tripId}", method = RequestMethod.POST)
    @ResponseBody
    public String addTrip(@ModelAttribute Trip trip, BindingResult result) {
        if (result.hasErrors()) {
            return "addTrip";
        }
        tripService.addTrip(trip);
        return "redirect:/getAllTrip";
    }

    @RequestMapping(value="trip/{tripId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTrip(@PathVariable(value="tripId") int tripId) {

        tripService.deleteTrip(tripId);
        return "redirect:/getAllTrip";
    }

    @RequestMapping(value="trip/{tripId}", method = RequestMethod.POST)
    @ResponseBody
    public String editTrip(@ModelAttribute Trip trip, @PathVariable(value="tripId") int tripId) {
        trip.setTripId(tripId);
        tripService.updateTrip(trip);
        return "redirect:/getAllTrip";
    }



}
