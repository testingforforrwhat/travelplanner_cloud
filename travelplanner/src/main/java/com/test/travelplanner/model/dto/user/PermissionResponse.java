package com.test.travelplanner.model.dto.user;

import com.test.travelplanner.model.entity.user.Menu;
import com.test.travelplanner.model.entity.user.Operate;

import java.util.List;

public class PermissionResponse {
    private List<Menu> menus;
    private List<Operate> operates;
    
    // getter、setter
    public List<Menu> getMenus() { return menus; }
    public void setMenus(List<Menu> menus) { this.menus = menus; }
    public List<Operate> getOperates() { return operates; }
    public void setOperates(List<Operate> operates) { this.operates = operates; }
}