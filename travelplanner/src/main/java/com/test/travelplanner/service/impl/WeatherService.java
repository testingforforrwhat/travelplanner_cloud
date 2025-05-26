package com.test.travelplanner.service.impl;

import com.alibaba.fastjson2.JSON;
import com.test.travelplanner.external.model.WeatherData.DayNightDetail;
import com.test.travelplanner.external.model.WeatherData.Info;
import com.test.travelplanner.external.model.WeatherData.WeatherData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WeatherService {  
    
    private final WebClient webClient;
    private final String aMapKey;  
    
    public WeatherService(@Value("${tencent.api-key}") String aMapKey) {
        this.aMapKey = aMapKey;  
        this.webClient = WebClient.create();  
    }  
    
    /**  
     * 根据城市名称获取天气数据  
     */  
    public Map<String, Object> getWeatherByCity(String city) {  
        // 城市编码查询  
        String adcode = getCityCode(city);  
        if (adcode == null) {  
            adcode = "110000"; // 默认北京  
        }  
        
        // 调用高德天气API  
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?key=" + aMapKey  
                + "&city=" + adcode + "&extensions=all";  
                
        return webClient.get()  
                .uri(url)  
                .retrieve()  
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();  
    }  
    
    /**  
     * 城市名转adcode (实际中可以缓存城市编码数据)  
     */
    public String getCityCode(String cityName) {
        // 调用高德地图地理编码API，这里简化为静态映射  
        Map<String, String> cityCodeMap = Map.of(
            "北京", "110000",  
            "上海", "310000",  
            "保定", "130600",  
            "涿州", "130681"  
        );  
        return cityCodeMap.getOrDefault(cityName, null);  
    }  
    
    /**  
     * 转换高德天气数据为前端所需格式  
     */  
    public Map<String, Object> convertToClientFormat(String amapData) {
        Map<String, Object> result = new HashMap<>();

        log.info(" ==> amapData: {}", amapData);

        WeatherData weatherData = JSON.parseObject(amapData, WeatherData.class);

        // 先判断数据有效性
        // amapData.get("status") 单独使用是一个对象，不是布尔值，不能作为条件表达式
        if (!"0".equals(String.valueOf(weatherData.getStatus()))) {
            // 处理错误情况  
            result.put("error", "无法获取天气数据");
            return result;
        }

//        Map<String, Object> resultData = (Map<String, Object>)amapData.get("result");
//        List<Map<String, Object>> forecasts = (List<Map<String, Object>>) resultData.get("result");
        if (weatherData.getResult().getForecast().isEmpty()) {
            result.put("error", "无天气数据");  
            return result;  
        }  
        
//        Map<String, Object> forecastData = forecasts.get(0);
//        List<Map<String, Object>> infos = (List<Map<String, Object>>) forecastData.get("casts");

        Info infos = weatherData.getResult().getForecast().get(0).getInfos().get(0);
        if (infos == null) {
            result.put("error", "无天气数据");  
            return result;  
        }  
        
        // 当前天气数据（使用第一天数据）  
        // Map<String, Object> today = infos.get(0);
        DayNightDetail today = infos.getDay();

        // 位置信息  
        Map<String, Object> location = new HashMap<>();  
        location.put("name", weatherData.getResult().getForecast().get(0).getCity());
        location.put("country", "中国");  
        location.put("province", weatherData.getResult().getForecast().get(0).getProvince());
        result.put("location", location);  
        
        // 当前天气信息  
        Map<String, Object> current = new HashMap<>();  
        current.put("temp_c", parseInteger(today.getTemperature()));
        current.put("humidity", parseInteger(today.getHumidity()));
        current.put("wind_kph", today.getWind_power()); // 高德API没有直接提供风速，这里给个固定值
        current.put("wind_dir", today.getWind_direction());
        current.put("pressure_mb", 1012); // 高德API没有气压，给个固定值  
        current.put("feelslike_c", parseInteger(today.getTemperature()) - 2); // 高德没有体感温度
        current.put("uv", 3); // 高德API没有UV指数，给个固定值  
        
        // 天气状况  
        Map<String, Object> condition = new HashMap<>();  
        condition.put("text", today.getWeather());
        condition.put("icon", getWeatherIcon(today.getWeather()));
        current.put("condition", condition);  
        
        result.put("current", current);  
        
        return result;  
    }  
    
    private Integer parseInteger(Object value) {  
        if (value == null) return 0;  
        try {  
            return Integer.parseInt(value.toString());  
        } catch (NumberFormatException e) {  
            return 0;  
        }  
    }  
    
    /**  
     * 根据天气文本获取对应图标URL  
     */  
    private String getWeatherIcon(String weather) {  
        Map<String, String> iconMap = Map.of(  
            "晴天", "https://cdn.weatherapi.com/weather/64x64/day/113.png",
            "多云", "https://cdn.weatherapi.com/weather/64x64/day/116.png",  
            "阴天", "https://cdn.weatherapi.com/weather/64x64/day/119.png",
            "小雨", "https://cdn.weatherapi.com/weather/64x64/day/296.png",  
            "中雨", "https://cdn.weatherapi.com/weather/64x64/day/302.png",  
            "大雨", "https://cdn.weatherapi.com/weather/64x64/day/308.png",  
            "雷阵雨", "https://cdn.weatherapi.com/weather/64x64/day/200.png",  
            "阵雨", "https://cdn.weatherapi.com/weather/64x64/day/176.png",  
            "雪天", "https://cdn.weatherapi.com/weather/64x64/day/338.png"
        );  
        return iconMap.getOrDefault(weather, "https://cdn.weatherapi.com/weather/64x64/day/116.png");  
    }  
}  