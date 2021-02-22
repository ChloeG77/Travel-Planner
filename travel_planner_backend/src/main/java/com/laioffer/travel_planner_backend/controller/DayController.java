//package com.laioffer.travel_planner_backend.controller;
//
//import com.laioffer.travel_planner_backend.entity.Day;
//import com.laioffer.travel_planner_backend.entity.Place;
//import com.laioffer.travel_planner_backend.entity.Stop;
//import com.laioffer.travel_planner_backend.entity.StopType;
//import com.laioffer.travel_planner_backend.service.DayService;
//import com.laioffer.travel_planner_backend.service.PlaceService;
//import com.laioffer.travel_planner_backend.service.StopService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class DayController {
//
//    @Autowired
//    private DayService dayService;
//
//    @Autowired
//    private PlaceService placeService;
//
//    @Autowired
//    private StopService stopService;
//
//    @RequestMapping(value = "/trip/{dayId}/{placeId}", method = RequestMethod.POST)
//    public String addPlace(@PathVariable long dayId, @PathVariable String placeId) {
//        // Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        Day day = dayService.getDayById(dayId);
//        Place place = placeService.getPlaceById(placeId);
//        dayService.addPlace(day, place);
//        return "redirect:/getAllDays";
//    }
//
//    @RequestMapping(value = "/trip/{dayId}/{placeId}", method = RequestMethod.DELETE)
//    public String deletePlace(@PathVariable long dayId, @PathVariable String placeId) {
//        // Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        Day day = dayService.getDayById(dayId);
//        Place place = placeService.getPlaceById(placeId);
//        dayService.deletePlace(day, place);
//        return "redirect:/getAllDays";
//    }
//
//    @RequestMapping(value = "/trip/route/gen/{dayId}", method = RequestMethod.POST)
//    public String getDayById(@PathVariable(value = "dayId") long dayId) {
//        Day day = dayService.getDayById(dayId);
//        dayService.generateDayPath(day);
//        return "redirect:/getAllDays";
//    }
//
//    @RequestMapping(value = "/trip/route/{dayId}", method = RequestMethod.GET)
//    public List<Stop> getRoute(@PathVariable(value = "dayId") long dayId) {
//        Day day = dayService.getDayById(dayId);
//        return day.getRoute();
//    }
//
//    @RequestMapping(value = "/trip/route/{dayId}", method = RequestMethod.POST)
//    public String setRoute(@ModelAttribute List<Stop> route, BindingResult result, @PathVariable long dayId) {
//        if (result.hasErrors()) {
//            return "setRoute";
//        }
//        Day day = dayService.getDayById(dayId);
//        dayService.setRoute(day, route);
//        return "redirect:/getAllDays";
//    }
//
//    @RequestMapping(value = "/trip/day/stop/", method = RequestMethod.POST)
//    public String setStopFuncTyep(@ModelAttribute Map<Long, StopType> stopTypes, BindingResult result) {
//        if (result.hasErrors()) {
//            return "setRoute";
//        }
//        for (Map.Entry<Long, StopType> entry : stopTypes.entrySet()) {
//            Stop stop = stopService.getStopById(entry.getKey());
//            stop.setType(entry.getValue());
//        }
//        return "redirect:/getAllDays";
//    }
//
//}
