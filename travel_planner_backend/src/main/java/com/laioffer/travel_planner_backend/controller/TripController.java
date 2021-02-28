package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Trip;
import com.laioffer.travel_planner_backend.entity.User;
import com.laioffer.travel_planner_backend.message.response.TripsResponse;
import com.laioffer.travel_planner_backend.service.PlaceService;
import com.laioffer.travel_planner_backend.service.TripService;
import com.laioffer.travel_planner_backend.service.UserDetailsServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TripController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private TripService tripService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private UserDetailsServiceImpl userService;
    
    @GetMapping(value = "trip/getTrip", params = "tripId")
    public Trip getTripById(@RequestParam long tripId) {
        return tripService.getTripById(tripId);
    }
    
    @GetMapping(value = "trip/getTrip", params = "tripName")
    public Trip getTripByName(@RequestParam String tripName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return tripService.getTripByTripName(username, tripName);
    }
    
    
    @GetMapping(value = "user/trip/getAll")
    public List<Trip> getAllTrips() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        return tripService.getTripsByUsername(username);
    }
    
    
    @GetMapping(value = "trip/day/getAll", params = "tripId")
    public List<Day> getTripDays(@RequestParam int tripId) {
        return tripService.getTripById(tripId).getDays();
    }
    
    
    @PostMapping("trip/newTrip")
    public ResponseEntity<?> addTrip(@RequestBody Trip trip, BindingResult result) {
//        System.out.println(trip);
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUserByUsername(username);
        List<Trip> trips = user.getAllTrips();
        for (Trip userTrip : trips) {
//            System.out.println("trip name " + userTrip.getName());
            if (userTrip.getName().equals(trip.getName())) {
                return new ResponseEntity<>("trip name exist", HttpStatus.CONFLICT);
            }
        }
        
        int numOfDays = trip.getNumDays();
        List<Day> days = new ArrayList<>();
        for (int i = 0; i < numOfDays; i++) {
            Day day = new Day();
            day.setTrip(trip);
            days.add(day);
        }
        trip.setUser(user);
        trip.setDays(days);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        trip.setDateCreated(dtf.format(now));
        trips.add(trip);
        user.setAllTrips(trips);
        userService.save(user);
        
        return ResponseEntity.ok(new TripsResponse(trips));
    }
    
    @PostMapping(value = "trip/place", params = {"tripId", "placeId"})
    public String addPlace(@RequestParam long tripId, @RequestParam String placeId) {
        Place place = placeService.addPlace(placeId);
        tripService.addPlace(tripId, place);
        return "redirect:/getAllTrip";
    }
    
    @DeleteMapping(value = "trip/place", params = {"tripId", "placeId"})
    public String deletePlace(@RequestParam long tripId, @RequestParam String placeId) {
        tripService.deletePlace(tripId, placeId);
        return "redirect:/getAllTrip";
    }
    
    @DeleteMapping("trip/deleteTrip")
    public ResponseEntity<?> deleteTrip(@RequestParam long tripId) {
        tripService.deleteTrip(tripId);
        
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUserByUsername(username);
        List<Trip> trips = user.getAllTrips();
        return ResponseEntity.ok(new TripsResponse(trips));
    }
    
    @PostMapping("trip/editTrip")
    public String editTrip(@RequestBody Trip trip) {
        tripService.updateTrip(trip);
        return "redirect:/getAllTrip";
    }
    
    @GetMapping("trip/privacy")
    public boolean getTripPrivacy(@RequestParam long tripId) {
        
        return tripService.getTripById(tripId).isPrivate();
    }
    
    @PostMapping(value = "trip/privacy", params = {"tripId", "privacy"})
    public String setTripPrivacy(@RequestParam long tripId, @RequestParam boolean privacy) {
        tripService.getTripById(tripId).setPrivate(privacy);
        return "redirect:/getAllTrip";
    }
    
    @GetMapping("trip/rating")
    public double getTripRating(@RequestParam long tripId) {
        return tripService.getTripById(tripId).getRating();
    }
    
    @PostMapping(value = "trip/rating", params = {"tripId", "rating"})
    public String setTripRating(@RequestParam long tripId, @RequestParam double rating) {
        tripService.getTripById(tripId).setRating(rating);
        return "redirect:/getAllTrip";
    }
    
}
