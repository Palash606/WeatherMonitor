package com.example.weathermonitor.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherApiResponse {

    @JsonProperty("name")
    private String cityName; // City name

    @JsonProperty("main")
    private Main main; // Main weather parameters (temperature, feels like, etc.)

    @JsonProperty("weather")
    private Weather[] weather; // Weather conditions array

    @JsonProperty("dt")
    private long dt; // Data update time (Unix timestamp)

    // Getters and Setters
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    // Inner class for Main weather data (temperature, etc.)
    public static class Main {
        @JsonProperty("temp")
        private double temp; // Temperature in Kelvin

        @JsonProperty("feels_like")
        private double feelsLike; // Perceived temperature in Kelvin

        // Getters and Setters
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
        }
    }

    // Inner class for Weather conditions (like Clear, Rain, etc.)
    public static class Weather {
        @JsonProperty("main")
        private String main; // Main weather condition

        // Getters and Setters
        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }
}
