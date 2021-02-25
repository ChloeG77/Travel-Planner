package com.laioffer.travel_planner_backend.repository;

import com.laioffer.travel_planner_backend.entity.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    
    List<City> findByNameAndStateAndCountry(String name, String state, String country);
}
