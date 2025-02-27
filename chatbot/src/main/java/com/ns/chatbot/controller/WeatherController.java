package com.ns.chatbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController {
    private final ChatClient chatClientWithoutInMemory;

    @GetMapping("/weather")
    ChatResponse getResponse(@RequestParam(defaultValue = "What's the weather like in Boston?") String message) {
        log.info("PROMPT TO LLM ==> {}", message);
        return getChatResponse(message);

    }

    /**
     * Retrieves a chat response using the provided message and invokes the weather tool to fetch the weather.
     *
     * @param message the message to prompt the AI model with.
     * @return a {@link ChatResponse} containing the AI's response.
     */
    private ChatResponse getChatResponse(String message) {
        // Triggering the weather service tool from the AI model and logging the response.
        return chatClientWithoutInMemory.prompt(message)
                .tools(new WeatherService())  // Reference to the weather instance for tools (this makes sure we are using the WeatherService as the tool)
                .call()
                .chatResponse();

    }
}

/**
 * WeatherService interacts with the chat client and integrates the weather functionality into a conversational
 * AI flow. It uses an external tool to provide weather information based on location.
 */
@RequiredArgsConstructor
@Slf4j
class WeatherService {

    /**
     * A tool method that fetches weather information based on the provided location.
     *
     * @param location the name of the city or state to get the weather for.
     * @return a string representation of the weather (e.g., temperature).
     */
    @Tool(description = "Fetches the weather for a given location")
    public String weatherByLocation(@ToolParam(description = "City or state name") String location) {
        log.info("Requesting weather information for location: {}", location);
        // Here, we'd normally call an external API to get the weather for the location.
        // For now, returning a hardcoded response.
        return "43°C";  // Example hardcoded response for demonstration purposes.
    }
}

