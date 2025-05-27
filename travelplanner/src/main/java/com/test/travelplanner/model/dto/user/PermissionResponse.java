package com.test.travelplanner.model.dto.user;

import com.test.travelplanner.model.entity.user.Menu;
import com.test.travelplanner.model.entity.user.Operate;

import java.util.List;
import java.util.Optional;

public class PermissionResponse {
    private Optional<List<Menu>> menus;
    private Optional<List<Operate>> operates;
    
    // getter、setter
    public Optional<List<Menu>> getMenus() { return menus; }
    public void setMenus(Optional<List<Menu>> menus) { this.menus = menus; }
    public Optional<List<Operate>> getOperates() { return operates; }
    public void setOperates(Optional<List<Operate>> operates) { this.operates = operates; }
}