package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Stop;
import com.laioffer.travel_planner_backend.entity.StopType;
import com.laioffer.travel_planner_backend.service.DayService;
import com.laioffer.travel_planner_backend.service.ItemNotFoundException;
import com.laioffer.travel_planner_backend.service.PlaceService;
import com.laioffer.travel_planner_backend.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DayController {

    @Autowired
    private DayService dayService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private StopService stopService;

    @PostMapping(value = "/trip/{dayId}/{placeId}")
    public Stop addPlace(@PathVariable long dayId, @PathVariable String placeId) {
        Day day = dayService.getDayById(dayId);
        Place place = placeService.getPlaceById(placeId);
        return dayService.addPlace(day, place);
    }

    @DeleteMapping(value = "/trip/{dayId}/{placeId}")
    public String deletePlace(@PathVariable long dayId, @PathVariable String placeId) {
        Day day = dayService.getDayById(dayId);
        Place place = placeService.getPlaceById(placeId);
        dayService.deletePlace(day, place);
        return "redirect:/getAllDays";
    }

    @PostMapping(value = "/trip/route/gen/{dayId}")
    public String getDayById(@PathVariable(value = "dayId") long dayId) {
        Day day = dayService.getDayById(dayId);
        dayService.generateDayPath(day);
        return "redirect:/getAllDays";
    }

    @GetMapping(value = "/trip/route/{dayId}")
    public List<Stop> getRoute(@PathVariable(value = "dayId") long dayId) {
        Day day = dayService.getDayById(dayId);
        return day.getRoute();
    }

    @PostMapping(value = "/trip/route/{dayId}")
    public String setRoute(@RequestBody List<Stop> route, BindingResult result, @PathVariable long dayId) {
        if (result.hasErrors()) {
            return "setRoute";
        }
        Day day = dayService.getDayById(dayId);
        dayService.setRoute(day, route);
        return "redirect:/getAllDays";
    }

    @PostMapping(value = "/trip/day/stop/")
    public String setStopFuncTyep(@RequestBody Map<Long, StopType> stopTypes, BindingResult result) {
        if (result.hasErrors()) {
            return "Set StopType Error";
        }
        for (Map.Entry<Long, StopType> entry : stopTypes.entrySet()) {
            Stop stop = stopService.getStopById(entry.getKey());
            stop.setType(entry.getValue());
        }
        return "success";
    }

}
