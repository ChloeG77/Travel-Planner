package com.laioffer.travel_planner_backend.service;

import com.laioffer.travel_planner_backend.entity.Stop;
import com.laioffer.travel_planner_backend.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StopService {
    
    @Autowired
    private StopRepository stopRepository;
    
    @Transactional
    public Stop getStopById(long stopId) {
        return stopRepository.findById(stopId).orElseThrow(() ->
            new ItemNotFoundException("Stop Not Found with -> StopId : " + stopId)
        );
    }
    
}