package com.laioffer.travel_planner_backend.service;


import com.laioffer.travel_planner_backend.dao.DayDao;
import com.laioffer.travel_planner_backend.entity.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayService {


    @Autowired
    private DayDao dayDao;

    public void addDay(Day day) {
        dayDao.addDay(day);
    }
    public void deleteDay(int dayId) {
        dayDao.deleteDay(dayId);
    }


}
