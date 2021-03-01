package com.laioffer.travel_planner_backend.repository;

import com.laioffer.travel_planner_backend.entity.Trip;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    
    List<Trip> findAllByUserEmail(String email);
    
    Trip findById(long tripId);
    
    Trip findByName(String name);
}