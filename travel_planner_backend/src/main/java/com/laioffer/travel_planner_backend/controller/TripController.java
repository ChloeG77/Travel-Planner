package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Trip;
import com.laioffer.travel_planner_backend.entity.User;
import com.laioffer.travel_planner_backend.service.TripService;
import com.laioffer.travel_planner_backend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("trip/id/{tripId}")
    public Trip getTripById(@PathVariable(value = "tripId") long tripId) {
        return tripService.getTripById(tripId);
    }

    @GetMapping("trip/name/{tripName}")
    public Trip getTripByName(@PathVariable(value = "tripName") String tripName) {
        return tripService.getTripByTripName(tripName);
    }

    @GetMapping("trip/username/{userEmail}")
    public List<Trip> getgetTripsByUserEmail(@PathVariable(value = "userEmail") String userEmail) {
        return tripService.getTripsByUserEmail(userEmail);
    }


    @GetMapping("trip/days/{tripId}")
    public List<Day> getTripDays(@PathVariable(value = "tripId") int tripId) {
        return tripService.getTripById(tripId).getDays();
    }


    @PostMapping("trip/newTrip")
    public String addTrip(@RequestBody Trip trip, BindingResult result) {
        System.out.println(trip);
        if (result.hasErrors()) {
            return "addTrip error";
        }
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        System.out.println(username);
        User user = userService.getUserByUsername(username);
        System.out.println((user.getEmail()));
        trip.setUser(user);
        List<Trip> trips = user.getAllTrips();
        for(Trip userTrip : trips) {
            System.out.print("trip name "+ userTrip.getName());
            if (userTrip.getName().equals(trip.getName())) {
                return "Trip name exist";
            }
        }
        trips.add(trip);
        user.setAllTrips(trips);
        userService.save(user);
//        tripService.saveTrip(trip);

        return "success";
    }

    @PostMapping("trip/place/{tripId}/{placeId}")
    public String addPlace(@PathVariable(value = "tripId") long tripId, @RequestBody Place place, BindingResult result) {
        if (result.hasErrors()) {
            return "addTrip error";
        }
        tripService.addPlace(tripId, place);
        return "redirect:/getAllTrip";
    }

    @DeleteMapping("trip/place/{tripId}/{placeId}")
    public String deletePlace(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "placeId") String placeId) {
        tripService.deletePlace(tripId, placeId);
        return "redirect:/getAllTrip";
    }
    @DeleteMapping("trip/{tripId}")
    public String deleteTrip(@PathVariable(value = "tripId") long tripId) {
        tripService.deleteTrip(tripId);
        return "redirect:/getAllTrip";
    }

    @PostMapping("trip/editTrip")
    public String editTrip(@RequestBody Trip trip) {
        tripService.updateTrip(trip);
        return "redirect:/getAllTrip";
    }

    @GetMapping("trip/privacy/{tripId}")
    public boolean getTripPrivacy(@PathVariable(value = "tripId") long tripId) {

        return tripService.getTripById(tripId).isPrivate();
    }

    @PostMapping("trip/privacy/{tripId}/{privacy}")
    public String setTripPrivacy(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "privacy") boolean privacy) {
        tripService.getTripById(tripId).setPrivate(privacy);
        return "redirect:/getAllTrip";
    }

    @GetMapping("trip/rating/{tripId}")
    public double getTripRating(@PathVariable(value = "tripId") long tripId) {

        return tripService.getTripById(tripId).getRating();
    }

    @GetMapping("trip/rating/{tripId}/{rating}")
    public String setTripRating(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "rating") double rating) {
        tripService.getTripById(tripId).setRating(rating);
        return "redirect:/getAllTrip";
    }

}
