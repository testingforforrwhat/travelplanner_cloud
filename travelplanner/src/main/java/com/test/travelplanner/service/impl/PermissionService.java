package com.test.travelplanner.service.impl;


import com.test.travelplanner.model.entity.user.Operate;
import com.test.travelplanner.model.entity.user.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {


    private final MenuRepository menuRepository;
    private final OperateRepository operateRepository;

    public PermissionService(MenuRepository menuRepository, OperateRepository operateRepository) {
        this.menuRepository = menuRepository;
        this.operateRepository = operateRepository;
    }

    @Cacheable(value = "userMenus", key = "#userId")
    public List<Menu> getUserMenus(Long userId) {
        return menuRepository.findMenusByUserId(userId);
    }
    
    @Cacheable(value = "userOperates", key = "#userId")
    public List<Operate> getUserOperates(Long userId) {
        return operateRepository.findOperatesByUserId(userId);
    }
}