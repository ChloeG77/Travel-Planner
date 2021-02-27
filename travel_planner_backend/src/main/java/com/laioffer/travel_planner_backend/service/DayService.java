package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Stop;
import com.laioffer.travel_planner_backend.entity.StopType;
import com.laioffer.travel_planner_backend.repository.DayRepository;
import com.laioffer.travel_planner_backend.repository.StopRepository;
import com.laioffer.travel_planner_backend.repository.TripRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DayService {
    
    @Autowired
    private DayRepository dayRepository;
    
    @Autowired
    private StopRepository stopRepository;
    
    @Autowired
    private TripRepository tripRepository;
    
    @Transactional
    public void setRoute(Day day, List<Stop> route) {
        day.setRoute(route);
        dayRepository.save(day);
    }
    
    @Transactional
    public List<Stop> generateDayPath(Day day) {
        Set<Stop> stops = day.getStops();
        List<Stop> route = planRoute(stops);
        day.setRoute(route);
        return route;
    }
    
    @Transactional
    public Day getDayById(long dayId) {
        return dayRepository.findById(dayId).orElseThrow(() ->
            new ItemNotFoundException("Day Not Found with -> DayId : " + dayId)
        );
    }
    
    @Transactional
    public Stop addPlace(Day day, Place place) {
        Stop newStop = new Stop();
        newStop.setDay(day);
        newStop.setPlace(place);
        newStop.setType(new StopType());
        return stopRepository.save(newStop);
        // Maybe add to day? this should automatically cascade to day
// 				Set<Stop> stops = day.getStops();
//				stops.add(newStop);
    }
    
    @Transactional
    public void deletePlace(Day day, Place place) {
        Set<Stop> stops = day.getStops();
        // System.out.println(stops.toString());
        List<Stop> toDelete = new ArrayList<>();
        for (Stop stop : stops) {
            if (stop.getPlace().equals(place)) {
                toDelete.add(stop);
            }
        }
        // System.out.println(toDelete.toString());
        for (Stop stop : toDelete) {
            deleteStop(day, stop);
        }
    }
    
    @Transactional
    public void deleteStop(Day day, Stop stop) {
        Set<Stop> stops = day.getStops();
        List<Stop> route = day.getRoute();
        stops.remove(stop);
        route.remove(stop);
        dayRepository.save(day);
    }
    
    private List<Stop> planRoute(Set<Stop> places) {
        List<Stop> route = new ArrayList<>(places);
        return route;
    }
    
    @Transactional
    public Day newDay(long tripId) {
        Day day = new Day();
        day.setTrip(tripRepository.findById(tripId));
        return dayRepository.save(day);
    }
}
