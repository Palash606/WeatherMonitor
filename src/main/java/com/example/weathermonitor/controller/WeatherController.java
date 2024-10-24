package com.example.weathermonitor.controller;

import com.example.weathermonitor.model.Alert;
import com.example.weathermonitor.service.WeatherApiResponse;
import com.example.weathermonitor.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Endpoint to get current weather data for a city
    @GetMapping("/current/{cityName}")
    public WeatherApiResponse getCurrentWeather(@PathVariable String cityName) {
        return weatherService.fetchWeatherData(cityName);
    }

    // Endpoint to check for alerts for a city based on current temperature
    public List<Alert> checkAlerts(@PathVariable String cityName) {
        // Fetch weather data for the city
        WeatherApiResponse weatherData = weatherService.fetchWeatherData(cityName);

        // Access the temperature from the 'main' object
        double currentTempKelvin = weatherData.getMain().getTemp();

        // Convert Kelvin to Celsius
        double currentTempCelsius = weatherService.convertKelvinToCelsius(currentTempKelvin);

        // Check and return any active alerts for the city
        return weatherService.checkAlerts(cityName, currentTempCelsius);
    }


    // Endpoint to manually trigger weather data update for the cities
    @PostMapping("/update")
    public String updateWeatherData() {
        weatherService.updateWeatherData();
        return "Weather data updated successfully.";
    }

    // Endpoint to get daily weather summary for a city
    @GetMapping("/summary/{cityName}")
    public WeatherApiResponse getDailyWeatherSummary(@PathVariable String cityName) {
        return weatherService.fetchWeatherData(cityName);
    }

    // Endpoint to add a new alert configuration
    @PostMapping("/alerts")
    public String addAlert(@RequestBody Alert alert) {
        weatherService.addAlert(alert);
        return "Alert added successfully.";
    }
}

