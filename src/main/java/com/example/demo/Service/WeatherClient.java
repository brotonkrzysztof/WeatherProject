package com.example.demo.Service;

import com.example.demo.dto.WeatherRequest;
import net.minidev.json.JSONObject;

import java.util.List;

public abstract class WeatherClient {
    public abstract List<JSONObject> getWeatherByCoordinates(WeatherRequest weatherRequest);
}
