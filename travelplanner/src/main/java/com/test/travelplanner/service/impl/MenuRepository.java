package com.test.travelplanner.service.impl;

import com.test.travelplanner.model.entity.user.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
  List<Menu> findMenusByUserId(Long userId);
}