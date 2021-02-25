package com.laioffer.travel_planner_backend.repository;

import com.laioffer.travel_planner_backend.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {

//	Optional<Day> findById(Long aLong);
//
//	Day findByDayId(long dayId);
//
//	Day save(Day day);

}
