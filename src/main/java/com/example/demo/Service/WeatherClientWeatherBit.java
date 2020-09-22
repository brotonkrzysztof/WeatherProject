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
public class WeatherClientWeatherBit extends WeatherClient {
    private static final String WEATHERBIT_API_BASE_URL = "https://api.weatherbit.io/v2.0/current";
    private static final Logger logger = LoggerFactory.getLogger(WeatherClientWeatherBit.class);
    private static final String appKey = "daa7ea4e3da849bd8d42838b498dd87d";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherClientWeatherBit() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<JSONObject> getWeatherByCoordinates(WeatherRequest weatherRequest) throws NumberFormatException {
        List<JSONObject> returnList = new ArrayList<>();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(WEATHERBIT_API_BASE_URL)
                .queryParam("lon", Double.parseDouble(weatherRequest.getLon()))
                .queryParam("lat", Double.parseDouble(weatherRequest.getLat()))
                .queryParam("key", appKey);

        returnList.add(restTemplate.getForObject(uriBuilder.toUriString(), JSONObject.class));

        return returnList;
    }

}
