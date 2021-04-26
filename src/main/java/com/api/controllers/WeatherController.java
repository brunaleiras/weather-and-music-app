package com.api.controllers;

import com.api.repository.WeatherRepository;
import com.api.models.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public Weather update(@RequestBody Weather weather){
        return weatherRepository.save(weather);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Weather> getAllWeather(){
        List<Weather> allWeatherResults = weatherRepository.findAll();
        return allWeatherResults;
    }

}
