package com.test.travelplanner.external.model.WeatherData;

import lombok.Data;

@Data  
public class WeatherData {  
    private Integer status;  
    private String message;  
    private String request_id;  
    private Result result;  
}  







