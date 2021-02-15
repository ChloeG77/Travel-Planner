package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.dao.DayDao;
import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Stop;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class DayService {
		
		@Autowired
		private DayDao dayDao;
		
		public void generateDayPath(Day day) {
				Set<Stop> places = day.getPlaces();
				ArrayList<Stop> route = planRoute(places);
				day.setRoute(route);
		}
		
		private ArrayList<Stop> planRoute(Set<Stop> places) {
				return null;
		}
		
		public Day getDayById(int dayId) {
				return dayDao.getDayById(dayId);
		}
		
}
