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

/**
 * Configuration class for the chatbot application.
 * <p>
 * This class defines all the beans required for the chatbot to function, including:
 * <ul>
 *   <li>Chat clients with different memory configurations</li>
 *   <li>HTTP clients for external API communication</li>
 *   <li>Service beans for weather information</li>
 * </ul>
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@org.springframework.context.annotation.Configuration
public class ChatbotConfiguration {
    
    /**
     * Creates a ChatClient with in-memory chat history.
     * <p>
     * This client maintains conversation context across multiple interactions,
     * allowing for more coherent multi-turn conversations.
     *
     * @param chatClientBuilder The builder for creating chat clients
     * @param inMemoryChatMemory The memory component for storing chat history
     * @return A ChatClient configured with memory and logging advisors
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, InMemoryChatMemory inMemoryChatMemory) {
        chatClientBuilder
                .defaultAdvisors(
                        new PromptChatMemoryAdvisor(inMemoryChatMemory),
                        new SimpleLoggerAdvisor())
                .build();
        return chatClientBuilder.build();
    }

    /**
     * Creates a ChatClient without in-memory chat history.
     * <p>
     * This client is stateless and treats each interaction independently,
     * which can be useful for one-off queries that don't require context.
     *
     * @param chatClientBuilder The builder for creating chat clients
     * @return A ChatClient configured with only logging advisors
     */
    @Bean
    public ChatClient chatClientWithoutInMemory(ChatClient.Builder chatClientBuilder) {
        chatClientBuilder
                .defaultAdvisors(
                        new SimpleLoggerAdvisor())
                .build();
        return chatClientBuilder.build();
    }

    /**
     * Creates an in-memory chat memory component.
     * <p>
     * This component stores conversation history in memory,
     * enabling contextual conversations with the AI model.
     *
     * @return An InMemoryChatMemory instance
     */
    @Bean
    InMemoryChatMemory inMemoryChatMemory() {
        return new InMemoryChatMemory();
    }

    /**
     * Creates a RestTemplate for making HTTP requests.
     * <p>
     * This bean is not created in the dev profile, allowing
     * for mock implementations during development and testing.
     *
     * @return A RestTemplate instance
     */
    @Bean
    @Profile("!dev")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Creates a function for accessing the OpenWeather service.
     * <p>
     * This function takes an OpenWeatherRequest with coordinates
     * and returns weather information for that location.
     *
     * @return A Function that processes weather requests
     */
    @Bean
    public Function<OpenWeatherRequest, OpenWeatherResponse> openWeatherService() {
        return new OpenWeatherProxyClient(restTemplate());
    }
}
