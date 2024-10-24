# Weather Monitor Application

## Overview

The **Weather Monitor Application** is a Spring Boot-based application that provides real-time weather updates and
forecasts for various locations. Users can easily search for weather information by city names, view current weather
conditions, and receive forecasts for the upcoming days. The application integrates with external weather APIs to fetch
the latest weather data.

## Features

- **Real-time Weather Updates**: Get current weather conditions for any city.
- **Forecasts**: Access weather forecasts for the next several days.
- **Location-Based Search**: Search for weather information using city names.
- **User-Friendly Interface**: A simple and intuitive interface for easy navigation.

## Technologies Used

- **Spring Boot**: Framework for building the RESTful backend.
- **Jakarta Persistence (JPA)**: For database interactions and ORM functionality.
- **Spring Security**: For application security (if applicable).
- **Thymeleaf**: For rendering dynamic web pages.
- **Maven**: Build automation tool.
- **H2 Database**: In-memory database for development and testing (if needed).
- **External Weather API**: Integration with third-party weather APIs for data retrieval.

## Installation

Follow these steps to get the application up and running:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/WeatherMonitor.git
   cd WeatherMonitor
2. **Build & run the project**:
   ```bash
   mvn clean install
   mvn spring-boot:run

