package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.entity.Place;
import com.laioffer.travel_planner_backend.entity.Stop;
import com.laioffer.travel_planner_backend.entity.StopType;
import com.laioffer.travel_planner_backend.service.DayService;
import com.laioffer.travel_planner_backend.service.PlaceService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DayController {
		@Autowired
		private DayService dayService;
		
		private PlaceService placeService;
		
		@RequestMapping(value = "/trip/{dayId}/{placeId}", method = RequestMethod.POST)
		public void addPlace(@PathVariable int dayId, @PathVariable String placeId) {
				// Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
				Day day = dayService.getDayById(dayId);
				Place place = placeService.getPlaceById(placeId);
				dayService.addPlace(day, place);
				
		}
		
		@RequestMapping(value = "/trip/{dayId}/{placeId}", method = RequestMethod.DELETE)
		public void deletePlace(@PathVariable int dayId, @PathVariable String placeId) {
				// Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
				Day day = dayService.getDayById(dayId);
				Place place = placeService.getPlaceById(placeId);
				dayService.deletePlace(day, place);
				
		}
		
		@RequestMapping(value = "/gen_path/{dayId}", method = RequestMethod.POST)
		public void getDayById(@PathVariable(value="dayId") int dayId){
				Day day = dayService.getDayById(dayId);
				dayService.generateDayPath(day);
		}
		

		
}
