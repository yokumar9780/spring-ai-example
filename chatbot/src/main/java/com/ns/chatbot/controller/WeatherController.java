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

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    private final ChatClient chatClientWithoutInMemory;
    private final WeatherService weatherService;

    @GetMapping("/weather")
    ChatResponse getWeatherByCountry(@RequestParam(defaultValue = "What's the weather like in Boston?") String message) {
        return getChatResponse(message);
    }

    @PostMapping("/weather")
    WeatherResponseDto getWeatherByLocationUsingOpenWeatherProxy(@RequestBody @Valid Location location) {
        return getChatResponseForLocation(location);

    }

    private ChatResponse getChatResponse(String message) {
        return chatClientWithoutInMemory.prompt(message)
                .tools(weatherService)  // Reference to the weather instance for tools (this makes sure we are using the WeatherService as the tool)
                .call()
                .chatResponse();

    }

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
                .tools(weatherService)  // Reference to the weather instance for tools (this makes sure we are using the WeatherService as the tool)
                .call()
                .entity(new ParameterizedTypeReference<WeatherResponseDto>() {
                });

    }
}

