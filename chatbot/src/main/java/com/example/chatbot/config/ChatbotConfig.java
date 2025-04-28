package com.example.chatbot.config;

import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherProxyClient;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherRequest;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Configuration
public class ChatbotConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(new InMemoryChatMemory()),
                        new SimpleLoggerAdvisor())
                .build();

    }

    @Bean
    @Profile("!dev")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Function<OpenWeatherRequest, OpenWeatherResponse> openWeatherService() {
        return new OpenWeatherProxyClient(restTemplate());
    }

}
