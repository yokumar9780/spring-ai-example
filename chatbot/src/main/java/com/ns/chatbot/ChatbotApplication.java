package com.ns.chatbot;

import com.ns.chatbot.config.proxy.ProxySettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Main application class for the Spring AI Chatbot.
 * <p>
 * This class initializes the Spring Boot application and enables
 * configuration properties for proxy settings.
 */
@SpringBootApplication
@EnableConfigurationProperties({ProxySettings.class})
public class ChatbotApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(ChatbotApplication.class, args);
    }

}
