package com.test.travelplanner.repository;

import com.test.travelplanner.model.entity.user.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}