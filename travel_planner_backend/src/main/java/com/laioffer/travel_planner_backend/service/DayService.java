package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.dao.DayDao;
import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class DayService {
		
		@Autowired
		private DayDao dayDao;
		
		public void generateDayPath(Day day) {
				Set<Place> places = day.getPlaces();
				ArrayList<Place> route = planRoute(places);
				day.setRoute(route);
		}
		
		private ArrayList<Place> planRoute(Set<Place> places) {
				return null;
		}
		
		public Day getDayById(int dayId) {
				return dayDao.getDayById(dayId);
		}
		
}
