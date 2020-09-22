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
public class WeatherClientAccuWeather extends WeatherClient {
    private static final String ACCUWEATHER_API_BASE_URL = "http://dataservice.accuweather.com/currentconditions/v1/";
    private static final String ACCUWEATHER_API_BASE_URL_SEARCH_KEY = "http://dataservice.accuweather.com/locations/v1/cities/geoposition/search";
    private static final Logger logger = LoggerFactory.getLogger(WeatherClientAccuWeather.class);
    private static final String appKey = "I9rEZ0DUZkgVEUJ1ypYvj5Ke8JB1Ccin";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherClientAccuWeather() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<JSONObject> getWeatherByCoordinates(WeatherRequest weatherRequest) {
        List<JSONObject> returnList = new ArrayList<>();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(ACCUWEATHER_API_BASE_URL_SEARCH_KEY)
                .queryParam("q", weatherRequest.getLat() +","+ weatherRequest.getLon())
                .queryParam("apikey", appKey);


        String key = restTemplate.getForObject(uriBuilder.toUriString(), JSONObject.class).get("Key").toString();

        JSONObject accuWeatherList[] =  restTemplate.getForObject(UriComponentsBuilder
                .fromUriString(ACCUWEATHER_API_BASE_URL + key)
                .queryParam("apikey", appKey).toUriString() , JSONObject[].class);

        for(JSONObject accuWeather : accuWeatherList)
            returnList.add(accuWeather);

        return returnList;
    }

}
