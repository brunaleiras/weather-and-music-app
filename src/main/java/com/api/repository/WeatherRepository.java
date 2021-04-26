package com.api.repository;

import com.api.entities.Weather;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, UUID>{


}
