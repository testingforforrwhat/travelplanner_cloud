package com.test.travelplanner.repository;

import com.test.travelplanner.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}