package com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.controller;


import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.model.Location;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.model.WeatherResponse;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final ChatClient chatClient;
    private final WeatherService weatherService;

    @GetMapping("/weather")
    WeatherResponse getWeatherByLocationUsingOpenWeatherProxy(@RequestBody @Valid Location location) {
        return getChatResponseForLocation(location);
    }

    private WeatherResponse getChatResponseForLocation(Location location) {
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
        return chatClient
                .prompt(message)
                .tools(weatherService)  // Reference to the weather instance for tools
                .call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }
}

