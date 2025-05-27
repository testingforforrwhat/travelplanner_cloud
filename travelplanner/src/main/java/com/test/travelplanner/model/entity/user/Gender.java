package com.test.travelplanner.model.entity.user;

public enum Gender {
    MALE("男"),
    FEMALE("女"),
    OTHER("其他"),
    UNKNOWN("未知");
    
    private final String description;
    
    Gender(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}