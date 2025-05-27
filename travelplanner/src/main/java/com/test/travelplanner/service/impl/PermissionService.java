package com.test.travelplanner.service.impl;


import com.test.travelplanner.model.entity.user.Operate;
import com.test.travelplanner.model.entity.user.Menu;
import com.test.travelplanner.model.entity.user.UserRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {


    private final MenuRepository menuRepository;
    private final OperateRepository operateRepository;

    public PermissionService(MenuRepository menuRepository, OperateRepository operateRepository) {
        this.menuRepository = menuRepository;
        this.operateRepository = operateRepository;
    }

    @Cacheable(value = "userMenus", key = "#userRole")
    public Optional<List<Menu>> getUserMenus(UserRole userRole) {
        return menuRepository.findMenusByPermissionId(userRole);
    }
    
    @Cacheable(value = "userOperates", key = "#userRole")
    public Optional<List<Operate>> getUserOperates(UserRole userRole) {
        return operateRepository.findOperatesByPermissionId(userRole);
    }
}