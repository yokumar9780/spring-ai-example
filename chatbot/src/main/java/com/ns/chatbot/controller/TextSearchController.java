package com.ns.chatbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TextSearchController {
    private final ChatClient chatClient;

    @GetMapping("/text")
    String getResponse(@RequestParam String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Can you please provide the detailed response for the the given topic "{topic}"?
                """);
        promptTemplate.add("topic", topic);
        String contents = promptTemplate.create().getContents();
        log.info("PROMPT TO LLM ==> {}", contents);
        return chatClient.prompt()
                .user(contents)
                .call()
                .content();

    }

}
