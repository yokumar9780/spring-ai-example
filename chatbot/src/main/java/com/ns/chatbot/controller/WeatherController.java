package com.ns.chatbot.controller;

import com.ns.chatbot.model.Location;
import com.ns.chatbot.model.WeatherResponseDto;
import com.ns.chatbot.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller for weather-related endpoints.
 * <p>
 * This controller provides:
 * <ul>
 *   <li>Text-based weather information for locations by name</li>
 *   <li>Detailed weather information for locations by coordinates</li>
 * </ul>
 * <p>
 * It demonstrates the use of Spring AI's tool calling capabilities
 * by delegating weather lookups to the WeatherService.
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    /**
     * Chat client without memory for stateless weather queries.
     */
    private final ChatClient chatClientWithoutInMemory;
    
    /**
     * Weather service that provides the actual weather data.
     */
    private final WeatherService weatherService;

    /**
     * Gets weather information for a location specified in a text message.
     *
     * @param message The message containing a location (default: "What's the weather like in Boston?")
     * @return A ChatResponse containing weather information
     */
    @GetMapping("/weather")
    ChatResponse getWeatherByCountry(@RequestParam(defaultValue = "What's the weather like in Boston?") String message) {
        return getChatResponse(message);
    }

    /**
     * Gets detailed weather information for a location specified by coordinates.
     *
     * @param location A Location object containing latitude and longitude
     * @return A WeatherResponseDto with detailed weather information and recommendations
     */
    @PostMapping("/weather")
    WeatherResponseDto getWeatherByLocationUsingOpenWeatherProxy(@RequestBody @Valid Location location) {
        return getChatResponseForLocation(location);
    }

    /**
     * Helper method to get a chat response for a text message.
     *
     * @param message The message to process
     * @return A ChatResponse from the AI model
     */
    private ChatResponse getChatResponse(String message) {
        return chatClientWithoutInMemory.prompt(message)
                .tools(weatherService)  // Reference to the weather instance for tools
                .call()
                .chatResponse();
    }

    /**
     * Helper method to get a structured weather response for a location.
     *
     * @param location The location to get weather for
     * @return A WeatherResponseDto with weather details and recommendations
     */
    private WeatherResponseDto getChatResponseForLocation(Location location) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                What's the weather like at location {latitude} and {longitude}?
                Please respond with all the possible details, and based on the weather details,
                please provide a suitable outfit and places to visit.
                Please provide all the output in json format
                """);
        promptTemplate.add("latitude", location.latitude());
        promptTemplate.add("longitude", location.longitude());
        String message = promptTemplate.create().getContents();
        log.info("PROMPT TO LLM ==> {}", message);
        // Triggering the weather service tool from the AI model and logging the response.
        return chatClientWithoutInMemory.prompt(message)
                .tools(weatherService)  // Reference to the weather instance for tools
                .call()
                .entity(new ParameterizedTypeReference<WeatherResponseDto>() {
                });
    }
}

