package com.example.chatbot.config;

import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.AssistantTool;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherProxyClient;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherRequest;
import com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.proxy.OpenWeatherResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

@Configuration
public class ChatbotConfig {


    @Bean
    InMemoryChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
        return new QuestionAnswerAdvisor(vectorStore,
                SearchRequest.builder()
                        .similarityThreshold(0.8d)
                        .topK(6) //return the top 6 results
                        .build());
    }

    @Bean
    @Qualifier("defaultChatClient")
    public ChatClient defaultChatClient(ChatClient.Builder chatClientBuilder, InMemoryChatMemory chatMemory) {
        return chatClientBuilder
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),//Memory is retrieved and added as a collection of messages to the prompt
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    CommandLineRunner ingestTermOfServiceToVectorStore(VectorStore vectorStore,
                                                       @Value("classpath:rag/terms-of-service.txt") Resource termsOfServiceDocs) {

        // Ingest the document into the vector store
        return args -> {
            List<Document> read = new TextReader(termsOfServiceDocs).read();
            List<Document> transform = new TokenTextSplitter().transform(read);
            vectorStore.write(transform);
        };
    }

    @Bean
    @Qualifier("bookingCustomerSupportAssistantChatClient")
    @Primary
    public ChatClient chatClientWithVector(ChatClient.Builder chatClientBuilder, InMemoryChatMemory chatMemory,
                                           QuestionAnswerAdvisor questionAnswerAdvisor, AssistantTool bookingTool) {
        return chatClientBuilder
                .defaultSystem("""
                        You are a customer chat support agent of an airline named "Funnair"."
                        Respond in a friendly, helpful, and joyful manner.
                        You are interacting with customers through an online chat system.
                        
                        Before answering a question about a booking or cancelling a booking, you MUST always
                        get the following information from the user: booking number, customer first name and last name.
                        
                        If you can not retrieve the status of my flight, please just say "I am sorry, I can not find the booking details".
                        
                        Check the message history for booking details before asking the user.
                        Before changing a booking you MUST ensure it is permitted by the terms.
                        If there is a charge for the change, you MUST ask the user to consent before proceeding.
                        Use the provided functions to fetch booking details, change bookings, and cancel bookings.
                        Today is {current_date}.
                        """) // When asked for users' flight details, do not rely on previous conversation memory (history) but always call the function.
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),//Memory is retrieved and added into the prompt’s system text.
                        //the QuestionAnswerAdvisor will perform a similarity search over all documents in the Vector Database.
                        // To restrict the types of documents that are searched, the SearchRequest takes an SQL like filter expression
                        // that is portable across all VectorStores.
                        questionAnswerAdvisor
                        //, new SimpleLoggerAdvisor()
                )
                .defaultTools(bookingTool)
                .build();
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
