package com.test.travelplanner.repository;

import com.test.travelplanner.model.entity.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}