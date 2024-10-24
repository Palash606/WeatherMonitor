package com.example.weathermonitor.service;

import com.example.weathermonitor.model.Alert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey; // OpenWeatherMap API key


    @Value("${openweathermap.api.url}")
    private String apiUrl;

    @Value("${openweatherapi.api.cities}")
    private String[] cities;

    @Value("${openweatherapi.api.interval}")
    private long interval;
    private final RestTemplate restTemplate;

    // List of user-configured alerts
    private List<Alert> activeAlerts = new ArrayList<>();

    // List of cities to monitor

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to fetch weather data from the OpenWeatherMap API
    public WeatherApiResponse fetchWeatherData(String city) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, apiKey);
        return restTemplate.getForObject(url, WeatherApiResponse.class);
    }

    // Scheduled method to run every 5 minutes
    @Scheduled(fixedRate = 300000) // 5 minutes in milliseconds
    public void updateWeatherData() {
        for (String city : cities) {
            WeatherApiResponse weatherData = fetchWeatherData(city);
            if (weatherData != null) {
                processWeatherData(weatherData);
            }
        }
    }

    // Process the retrieved weather data
    public void processWeatherData(WeatherApiResponse weatherData) {
        String cityName = weatherData.getCityName();
        double currentTempCelsius = convertKelvinToCelsius(weatherData.getMain().getTemp());
        double feelsLikeTempCelsius = convertKelvinToCelsius(weatherData.getMain().getFeelsLike());
        String dominantWeatherCondition = weatherData.getWeather()[0].getMain();

        // Check for alerts based on the current weather
        checkAlerts(cityName, currentTempCelsius);

        // Print the daily weather information
        System.out.printf("City: %s | Current Temp: %.2f°C | Feels Like: %.2f°C | Condition: %s%n",
                cityName, currentTempCelsius, feelsLikeTempCelsius, dominantWeatherCondition);
    }

    // Check alerts based on the current weather data
    public List<Alert> checkAlerts(String cityName, double currentTempCelsius) {
        List<Alert> alertList = new ArrayList<>();

        for (Alert alert : activeAlerts) {
            if (currentTempCelsius > alert.getTemperatureThreshold()) {
                Alert newAlert = new Alert(currentTempCelsius, cityName);
                alertList.add(newAlert);
                System.out.printf("Alert! %s: Current temperature exceeds %.1f°C.%n", cityName, alert.getTemperatureThreshold());
            }
        }
        return alertList;
    }

    // Convert temperature from Kelvin to Celsius
    public double convertKelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    // Add a method to add alerts (this can be called from a controller)
    public void addAlert(Alert alert) {
        activeAlerts.add(alert);
    }
}
