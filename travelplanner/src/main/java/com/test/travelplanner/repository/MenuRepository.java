package com.test.travelplanner.repository;

import com.test.travelplanner.model.entity.user.Menu;
import com.test.travelplanner.model.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

  @Query(value = """
        SELECT DISTINCT m.* 
        FROM "role" r
        JOIN role_permission rp ON r.role_id = rp.role_id
        JOIN permission p ON rp.permission_id = p.permission_id
        JOIN permission_operate po ON p.permission_id = po.permission_id
        JOIN menu m ON po.operate_id = m.menu_id
        WHERE r.role_name = :userRole
        ORDER BY m.menu_id
        """, nativeQuery = true)
  Optional<List<Menu>> findMenusByPermissionId(UserRole userRole);
}