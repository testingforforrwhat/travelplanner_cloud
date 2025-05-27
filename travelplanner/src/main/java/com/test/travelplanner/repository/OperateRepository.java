package com.test.travelplanner.repository;

import com.test.travelplanner.model.entity.user.Operate;
import com.test.travelplanner.model.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OperateRepository extends JpaRepository<Operate, Integer> {

    @Query(value = """
        SELECT DISTINCT o.* 
        FROM "role" r
        JOIN role_permission rp ON r.role_id = rp.role_id
        JOIN permission p ON rp.permission_id = p.permission_id
        JOIN permission_operate po ON p.permission_id = po.permission_id
        JOIN operate o ON po.operate_id = o.operate_id
        WHERE r.role_name = :userRole
        ORDER BY o.operate_id
        """, nativeQuery = true)
    Optional<List<Operate>> findOperatesByPermissionId(String userRole);
}