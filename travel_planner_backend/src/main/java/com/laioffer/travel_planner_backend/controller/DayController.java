package com.laioffer.travel_planner_backend.controller;

import com.laioffer.travel_planner_backend.entity.Day;
import com.laioffer.travel_planner_backend.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DayController {

    @Autowired
    private DayService dayService;


    @RequestMapping(value = "day/addDay", method = RequestMethod.POST)
    public String addDay (@ModelAttribute Day day, BindingResult result) {
        if(result.hasErrors()) {
            return "addDay";
        }
        dayService.addDay(day);
        return "redirect:/getAllDays";

    }

    @RequestMapping (value = "delete/dayId")
    public String deleteProduct(@PathVariable(value = "dayId") int dayId) {
        dayService.deleteDay(dayId);
        return "redirect:/getAllDays";
    }


}
