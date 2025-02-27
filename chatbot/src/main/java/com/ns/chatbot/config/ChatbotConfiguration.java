package com.ns.chatbot.config;


import com.ns.chatbot.proxy.OpenWeatherProxyClient;
import com.ns.chatbot.proxy.OpenWeatherRequest;
import com.ns.chatbot.proxy.OpenWeatherResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@org.springframework.context.annotation.Configuration
public class ChatbotConfiguration {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, InMemoryChatMemory inMemoryChatMemory) {
        chatClientBuilder
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(inMemoryChatMemory),
                        new SimpleLoggerAdvisor())
                .build();
        return chatClientBuilder.build();
    }

    @Bean
    public ChatClient chatClientWithoutInMemory(ChatClient.Builder chatClientBuilder) {
        chatClientBuilder
                .defaultAdvisors(
                        new SimpleLoggerAdvisor())
                .build();
        return chatClientBuilder.build();
    }

    @Bean
    InMemoryChatMemory inMemoryChatMemory() {
        return new InMemoryChatMemory();
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
