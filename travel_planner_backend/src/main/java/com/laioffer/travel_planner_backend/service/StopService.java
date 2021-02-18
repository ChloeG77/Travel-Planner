package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.dao.StopDao;
import com.laioffer.travel_planner_backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StopService {

    @Autowired
    private StopDao stopDao;

    public Stop getStopById(long stopId) {
        return stopDao.getStopById(stopId);
    }

}