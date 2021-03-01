package com.laioffer.travel_planner_backend.repository;

import com.laioffer.travel_planner_backend.entity.Trip;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    
    List<Trip> findAllByUser_Username(String username);
    
    Trip findByUser_UsernameAndName(String username, String name);
    
    Trip findById(long tripId);
    
}