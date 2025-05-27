package com.test.travelplanner.repository;

import com.test.travelplanner.model.entity.user.PermissionMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionMenuRepository extends JpaRepository<PermissionMenu, Long> {
}