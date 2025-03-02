package com.ns.chatbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for text-based AI responses.
 * <p>
 * This controller provides a simple endpoint for getting
 * AI-generated responses on any topic. It demonstrates
 * basic prompt engineering with Spring AI.
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class TextSearchController {
    /**
     * The chat client used to communicate with the AI model.
     */
    private final ChatClient chatClient;

    /**
     * Gets an AI-generated response on the specified topic.
     *
     * @param topic The topic to get information about
     * @return A text response from the AI model
     */
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
