package com.example.chatbot.usecase.airline_booking_customer_suuport_agent;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConsoleChatRunner implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final AssistantController assistantController;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Spring AI Console Chat!");
        System.out.println("Type 'exit' to quit the chat.");

        // Generate a chat ID for this session
        String chatId = UUID.randomUUID().toString();
        System.out.println("Chat session ID: " + chatId);
        while (true) {
            System.out.print("\nEnter your message: ");
            String userMessage = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userMessage.trim())) {
                System.out.println("Exiting chat. Goodbye!");
                break;
            }

            System.out.println("\nAI is responding...");
            try {
                // Get the complete response
                String response = assistantController.chat(chatId, userMessage);
                System.out.println(response);
            } catch (Exception e) {
                System.err.println("Error getting response: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
