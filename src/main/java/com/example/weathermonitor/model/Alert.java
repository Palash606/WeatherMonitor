package com.example.weathermonitor.model;

public class Alert {

    private double temperatureThreshold; // Threshold temperature for alert
    private String condition; // Weather condition to alert on (e.g., "Rain", "Snow")

    public Alert(double temperatureThreshold, String condition) {
        this.temperatureThreshold = temperatureThreshold;
        this.condition = condition;
    }

    public double getTemperatureThreshold() {
        return temperatureThreshold;
    }

    public void setTemperatureThreshold(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
