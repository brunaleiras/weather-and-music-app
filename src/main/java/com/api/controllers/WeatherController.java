package com.api.controllers;

import com.api.repository.WeatherRepository;
import com.api.entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final String URL_OPEN_WEATHER = "api.openweathermap.org/data/2.5/weather";
    private final String APP_ID_OPEN_WEATHER = "6801fe9e74c3fd9d5a5b0ea6b668d7af";
    private final String URL_SPOTIFY_API = "api.spotify.com/v1/search";
    //https://api.spotify.com/v1/search?q=rock&type=track
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

    @GetMapping(value = "/temperature/{city}/{tokenSpotify}", produces = "application/json")
    public com.api.models.Weather getWeather(@PathVariable(value = "city") String city, @PathVariable(value = "tokenSpotify") String tokenSpotify) throws IOException{
        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(URL_OPEN_WEATHER)
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand(city,APP_ID_OPEN_WEATHER);

        String uri = uriComponents.toUriString();

        ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
        ObjectMapper mapper = new ObjectMapper();
        com.api.models.Weather weather = mapper.readValue(resp.getBody(), com.api.models.Weather.class);
        String genreMusicByTemperature = null;

        String valueTemperature = String.format("%.0f", weather.getTemp());
        Integer numTemperature = Integer.valueOf(valueTemperature.substring(0, valueTemperature.length() - 1));

        if(numTemperature > 30){
            genreMusicByTemperature = "party";
        }else if(numTemperature > 15 && numTemperature < 30){
            genreMusicByTemperature = "pop";
        }else if(numTemperature > 10 && numTemperature < 14){
            genreMusicByTemperature = "rock";
        }else{
            genreMusicByTemperature = "classic";
        }

        UriComponents urlSpotify = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(URL_SPOTIFY_API)
                .path("")
                .query("q={keyword}&type={type}")
                .buildAndExpand(genreMusicByTemperature,"track");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+tokenSpotify);
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> responseSpotify = restTemplate.exchange(urlSpotify.toUriString(), HttpMethod.GET, entity, String.class);

        //Parei Aqui

        return weather;
    }
}
