package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.dao.DayDao;
import com.laioffer.travel_planner_backend.dao.StopDao;
import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Stop;
import com.laioffer.travel_planner_backend.entity.StopType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DayService {

    @Autowired
    private DayDao dayDao;

    @Autowired
    private StopDao stopDao;

    public void setRoute(Day day, List<Stop> route) {
        day.setRoute(route);
        dayDao.update(day);
    }

    public void generateDayPath(Day day) {
        Set<Stop> stops = day.getStops();
        ArrayList<Stop> route = planRoute(stops);
        day.setRoute(route);
    }

    private ArrayList<Stop> planRoute(Set<Stop> places) {
        return null;
    }

    public Day getDayById(long dayId) {
        return dayDao.getDayById(dayId);
    }

    public void addPlace(Day day, Place place) {
        Stop newStop = new Stop();
        newStop.setDay(day);
        newStop.setPlace(place);
        newStop.setType(new StopType());
        stopDao.update(newStop);
        // Maybe add to day? this should automatically cascade to day
// 				Set<Stop> stops = day.getStops();
//				stops.add(newStop);
    }

    public void deletePlace(Day day, Place place) {
        Set<Stop> stops = day.getStops();
        List<Stop> toDelete = new ArrayList<>();
        for (Stop stop : stops) {
            if (stop.getPlace().equals(place)) {
                toDelete.add(stop);
            }
        }
        for (Stop stop : toDelete) {
            this.deleteStop(stop);
        }
    }

    public void deleteStop(Stop stop) {
        stopDao.deleteStop(stop);
    }
}
