package com.ns.chatbot.controller;

import com.ns.chatbot.fakedatagenator.model.Movie;
import com.ns.chatbot.fakedatagenator.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FakeDataGeneratorController {
    private final ChatClient chatClient;

    @GetMapping("/movies")
    List<Movie> getMovies(@RequestParam(defaultValue = "10") Integer count,
                          @RequestParam(defaultValue = "comedy") String category,
                          @RequestParam(defaultValue = "2025") String year) {

        PromptTemplate promptTemplate = new PromptTemplate("""
                Provide a list of top {count} movies for the specified {category} category and {year} year.
                Each object should contain an auto-incremented id field.
                """);
        Prompt prompt = promptTemplate.create(Map.of("count", count, "category", category, "year", year));

        String text = prompt.getContents();
        log.info("PROMPT TO LLM ==> {}", text);
        return chatClient.prompt()
                .user(text)
                .call()
                .entity(new ParameterizedTypeReference<List<Movie>>() {
                });
    }

    @GetMapping("/movies/{id}")
    Movie findMovieById(@PathVariable String id) {
        PromptTemplate pt = new PromptTemplate("""
                Find and return the object with id {id} in a current list of top movies.
                """);
        Prompt p = pt.create(Map.of("id", id));
        ChatClient.CallResponseSpec call = this.chatClient
                .prompt(p)
                //.user(p.getContents())
                .call();
        return call.entity(Movie.class);
    }

    @GetMapping("/persons")
    List<Person> getPersons(@RequestParam(defaultValue = "10") Integer count) {
        PromptTemplate pt = new PromptTemplate("""
                Return a current list of {count} most famous persons.
                Each object should contain an auto-incremented id field.
                Do not include any explanations or additional text.
                """);
        pt.add("count", count);
        return chatClient.prompt(pt.create())
                .call()
                .entity(new ParameterizedTypeReference<List<Person>>() {
                });
    }

}
