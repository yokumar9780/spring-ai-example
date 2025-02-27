package com.ns.chatbot.service;


import com.ns.chatbot.proxy.OpenWeatherProxyClient;
import com.ns.chatbot.proxy.OpenWeatherRequest;
import com.ns.chatbot.proxy.OpenWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * WeatherService interacts with the chat client and integrates the weather functionality into a conversational
 * AI flow. It uses an external tool to provide weather information based on location.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class WeatherService {
    private final OpenWeatherProxyClient openWeatherService;

    /**
     * A tool method that fetches weather information based on the provided location.
     *
     * @param location the name of the city or state to get the weather for.
     * @return a string representation of the weather (e.g., temperature).
     */
    @Tool(description = "Fetches the weather for a given location")
    public String weather(@ToolParam(description = "City or state name") String location) {
        log.info("Requesting weather information for location: {}", location);
        // Here, we'd normally call an external API to get the weather for the location.
        // For now, returning a hardcoded response.
        return "43°C";  // Example hardcoded response for demonstration purposes.
    }

    @Tool(description = "Fetches the weather for a given location")
    public OpenWeatherResponse weatherByLocation(@ToolParam(description = "latitude") double latitude
            , @ToolParam(description = "longitude") double longitude) {
        log.info("Requesting weather information for location: {}- {}", latitude, longitude);
        OpenWeatherRequest openWeatherRequest = OpenWeatherRequest.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
        return openWeatherService.apply(openWeatherRequest);
    }
}

