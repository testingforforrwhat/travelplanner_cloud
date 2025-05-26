package com.test.travelplanner.model;

public enum DeliveryMethod {
    ROBOT("ROBOT"),
    DRONE("DRONE"),;

    private final String description;

    DeliveryMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
