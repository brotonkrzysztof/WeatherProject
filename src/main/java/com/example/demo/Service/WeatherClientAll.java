package com.example.demo.Service;

import com.example.demo.Const.ProviderNames;
import com.example.demo.dto.WeatherRequest;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherClientAll extends WeatherClient {
    private static final Logger logger = LoggerFactory.getLogger(WeatherClientAll.class);

    @Autowired
    private WeatherClientFactory weatherClientFactory;

    @Override
    public List<JSONObject> getWeatherByCoordinates(WeatherRequest weatherRequest) {
        List<JSONObject> returnList = new ArrayList<>();

        returnList.addAll(weatherClientFactory.getWeatherByCondition(ProviderNames.OpenWeather.toString()).getWeatherByCoordinates(weatherRequest));
        returnList.addAll(weatherClientFactory.getWeatherByCondition(ProviderNames.WeatherBit.toString()).getWeatherByCoordinates(weatherRequest));
        returnList.addAll(weatherClientFactory.getWeatherByCondition(ProviderNames.AccuWeather.toString()).getWeatherByCoordinates(weatherRequest));

        return returnList;
    }
}
