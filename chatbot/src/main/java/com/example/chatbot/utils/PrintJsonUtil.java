package com.example.chatbot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public final class PrintJsonUtil {
    private PrintJsonUtil() {
        throw new IllegalStateException("PrintJsonUtil class");
    }

    public static void log(Object source, String... parameters) {
        // Asynchronous execution using CompletableFuture
        CompletableFuture.runAsync(() -> {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                log.debug("JSON MESSAGE FROM CLASS ::[{}]", (Object) parameters);
                log.debug(objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(source));
            } catch (Exception ignore) {
                //ignore
                log.error("Failed to pretty print JSON. [{}]", ignore.getMessage());
            }
        });
    }
}
