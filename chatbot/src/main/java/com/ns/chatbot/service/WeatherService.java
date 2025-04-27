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
 * WeatherService integrates weather functionality into conversational AI flows.
 * <p>
 * This service:
 * <ul>
 *   <li>Exposes weather information as AI tools using Spring AI annotations</li>
 *   <li>Provides methods for retrieving weather by location name or coordinates</li>
 *   <li>Integrates with the OpenWeatherProxyClient for actual data retrieval</li>
 * </ul>
 * <p>
 * The @Tool annotations make these methods available to AI models for function calling,
 * allowing the AI to request weather information during conversations.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class WeatherService {
    /**
     * The proxy client for accessing the OpenWeather API.
     */
    private final OpenWeatherProxyClient openWeatherService;

    /**
     * Retrieves weather information for a given location by name.
     * <p>
     * This method is annotated as a Tool, making it available to AI models
     * for function calling during conversations.
     *
     * @param location The name of the city or state to get weather for
     * @return A string representation of the weather (e.g., temperature)
     */
    @Tool(description = "Fetches the weather for a given location")
    public String weather(@ToolParam(description = "City or state name") String location) {
        log.info("Requesting weather information for location: {}", location);
        // Here, we'd normally call an external API to get the weather for the location.
        // For now, returning a hardcoded response.
        return "43°C";  // Example hardcoded response for demonstration purposes.
    }

    /**
     * Retrieves detailed weather information for a location specified by coordinates.
     * <p>
     * This method is annotated as a Tool, making it available to AI models
     * for function calling during conversations. It returns a more detailed
     * weather response object with comprehensive weather data.
     *
     * @param latitude  The latitude coordinate of the location
     * @param longitude The longitude coordinate of the location
     * @return A detailed OpenWeatherResponse object with weather information
     */
    @Tool(description = "Fetches the weather for a given location")
    public OpenWeatherResponse weatherByLocation(
            @ToolParam(description = "latitude") double latitude,
            @ToolParam(description = "longitude") double longitude) {
        log.info("Requesting weather information for location: {}- {}", latitude, longitude);
        OpenWeatherRequest openWeatherRequest = OpenWeatherRequest.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
        return openWeatherService.apply(openWeatherRequest);
    }
}

