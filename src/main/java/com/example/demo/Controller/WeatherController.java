package com.example.demo.Controller;


import com.example.demo.Service.*;
import com.example.demo.dto.WeatherRequest;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherClientFactory weatherClientFactory;

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @PostMapping("/getWeatherByCoordinates")
    public List<JSONObject> getWeather(@RequestBody WeatherRequest wr) {
        return weatherClientFactory.getWeatherByCondition(wr.getProviderName()).getWeatherByCoordinates(wr);
    }
}
