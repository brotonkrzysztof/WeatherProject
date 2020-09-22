package com.example.demo.Service;

import com.example.demo.Const.ProviderNames;
import org.springframework.stereotype.Service;

@Service
public class WeatherClientFactory {

    public static WeatherClient getWeatherByCondition(String weatherType){
        if(weatherType.equalsIgnoreCase(ProviderNames.OpenWeather.toString().toLowerCase()))
            return new WeatherClientOpenWeather();
        else if(weatherType.equalsIgnoreCase(ProviderNames.WeatherBit.toString().toLowerCase()))
            return new WeatherClientWeatherBit();
        else if(weatherType.equalsIgnoreCase(ProviderNames.AccuWeather.toString().toLowerCase()))
            return new WeatherClientAccuWeather();
        else if(weatherType.equalsIgnoreCase(ProviderNames.All.toString().toLowerCase()))
            return new WeatherClientAll();
        else
            throw new UnsupportedOperationException ();

    }
}
