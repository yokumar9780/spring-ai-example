package com.example.chatbot.usecase.vehicle_fake_data_generator;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
class VehicleChatbotController {

    private final ChatClient chatClient;

    @GetMapping("/vehicles")
    List<Vehicle> getVehicles(@RequestParam(defaultValue = "10") Integer count) {

        PromptTemplate promptTemplate = new PromptTemplate("""
                Generate a JSON array containing details for {count} trucks.
                Each truck object must include an auto-incremented id field starting from 1.
                 Please provide all the output in json format.
                """);
        Prompt prompt = promptTemplate.create(Map.of("count", count));
        String text = prompt.getContents();
        log.info("PROMPT TO LLM ==> {}", text);
        return chatClient.prompt()
                .user(text)
                .call()
                .entity(new ParameterizedTypeReference<List<Vehicle>>() {
                });
    }
}