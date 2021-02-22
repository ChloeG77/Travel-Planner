package com.laioffer.travel_planner_backend.repository;


import com.laioffer.travel_planner_backend.entity.Role;
import com.laioffer.travel_planner_backend.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}