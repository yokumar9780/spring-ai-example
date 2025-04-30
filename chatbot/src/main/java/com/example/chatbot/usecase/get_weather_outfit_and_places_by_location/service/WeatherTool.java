package com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.service;


import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherProxyClient;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherRequest;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class WeatherTool {
    private final OpenWeatherProxyClient openWeatherProxyClient;

    @Tool(name = "weatherByLocation",
            description = "Fetches the weather information based on latitude and longitude.")
    public OpenWeatherResponse weatherByLocation(
            @ToolParam(description = "Latitude of the location.") double latitude,
            @ToolParam(description = "Longitude of the location.") double longitude) {

        log.info("Fetching weather for latitude: {}, longitude: {}", latitude, longitude);

        OpenWeatherRequest request = OpenWeatherRequest.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();

        return openWeatherProxyClient.apply(request);
    }
}


