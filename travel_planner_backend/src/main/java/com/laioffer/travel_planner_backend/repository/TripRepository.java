package com.laioffer.travel_planner_backend.repository;

import com.laioffer.travel_planner_backend.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByUserEmail(String email);
    Trip findById(long tripId);
    Trip findByName(String name);
}