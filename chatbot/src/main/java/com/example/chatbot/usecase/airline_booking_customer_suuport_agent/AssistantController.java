package com.example.chatbot.usecase.airline_booking_customer_suuport_agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@RestController
@RequestMapping("/api/chat")
public class AssistantController {

    private final ChatClient chatClient;

    public AssistantController(@Qualifier(value = "bookingCustomerSupportAssistantChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/{chatId}")
    public String chat(@PathVariable String chatId, @RequestBody Map<String, String> body) {
        String message = body.get("message");
        return chat(chatId, message);
    }

    public String chat(String chatId, String userMessageContent) {
        return this.chatClient.prompt()
                .system(s -> s.param("current_date", LocalDate.now().toString()))
                .user(userMessageContent)
                .advisors(a -> a
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .call()
                .content();
    }
}
