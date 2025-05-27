package com.test.travelplanner.model.entity.user;

public enum UserStatus {
    ACTIVE("激活"),
    INACTIVE("未激活"),
    SUSPENDED("暂停"),
    DELETED("已删除"),
    LOCKED("锁定");
    
    private final String description;
    
    UserStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}