package com.laioffer.travel_planner_backend.repository;


import com.laioffer.travel_planner_backend.entity.Role;
import com.laioffer.travel_planner_backend.entity.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(RoleName roleName);
}