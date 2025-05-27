package com.test.travelplanner.service.impl;

import com.test.travelplanner.model.entity.user.Operate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperateRepository extends JpaRepository<Operate, Integer> {
    List<Operate> findOperatesByUserId(Long userId);
}