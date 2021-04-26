package com.api.controllers;

import com.api.repository.WeatherRepository;
import com.api.entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final String URL_OPEN_WEATHER = "api.openweathermap.org/data/2.5/weather";
    private final String APP_ID_OPEN_WEATHER = "6801fe9e74c3fd9d5a5b0ea6b668d7af";
    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired(required=true)
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public Weather update(@RequestBody Weather weather){
        return weatherRepository.save(weather);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Weather> getAllWeather(){
        List<Weather> allWeatherResults = weatherRepository.findAll();
        return allWeatherResults;
    }


    @GetMapping(value = "/weather", produces = "application/json")
    public com.api.models.Weather getWeather() throws IOException{
        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(URL_OPEN_WEATHER)
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand("Rio de Janeiro",APP_ID_OPEN_WEATHER);

        String uri = uriComponents.toUriString();

        ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        com.api.models.Weather weather = mapper.readValue(resp.getBody(), com.api.models.Weather.class);
        return weather;
    }
}
