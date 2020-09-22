package com.example.demo.Service;

import com.example.demo.dto.WeatherRequest;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


@Service
public class WeatherClientOpenWeather extends WeatherClient{
    private static final String OPENWEATHERMAP_API_BASE_URL = "https://api.OpenWeatherMap.org/data/2.5/weather";
    private static final Logger logger = LoggerFactory.getLogger(WeatherClientOpenWeather.class);
    private static final String appKey = "cbf74ec0a4bd9b7566b1021673c91060";


    private final RestTemplate restTemplate;

    @Autowired
    public WeatherClientOpenWeather() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<JSONObject> getWeatherByCoordinates(WeatherRequest weatherRequest) {
        List<JSONObject> returnList = new ArrayList<>();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(OPENWEATHERMAP_API_BASE_URL)
                .queryParam("lon", Integer.parseInt(weatherRequest.getLon()))
                .queryParam("lat", Integer.parseInt(weatherRequest.getLat()))
                .queryParam("apikey", appKey);

         returnList.add(restTemplate.getForObject(uriBuilder.toUriString(), JSONObject.class));

        return returnList;
    }
}
