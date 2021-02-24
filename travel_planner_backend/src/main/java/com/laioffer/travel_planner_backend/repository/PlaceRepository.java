package com.laioffer.travel_planner_backend.repository;

import com.laioffer.travel_planner_backend.entity.Place;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findById(long placeId);

    Place findByName(String name);
}
