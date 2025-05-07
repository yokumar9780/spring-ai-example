package com.example.chatbot.usecase.airline_booking_customer_suuport_agent;

import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.Booking;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.BookingClass;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.BookingStatus;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.Customer;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.repository.BookingRepository;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final VectorStore vectorStore;
    @Value("classpath:rag/terms-of-service.txt")
    private Resource termsOfServiceDocs;


    private void ingestTermOfServiceToVectorStore() {
        log.debug("Adding vector store data from terms of service");
        // Ingest the document into the vector store
        List<Document> read = new TextReader(termsOfServiceDocs).read();
        List<Document> transform = new TokenTextSplitter().transform(read);
        vectorStore.write(transform);

    }

    @Override
    public void run(String... args) {
        log.debug(">>>>>>>>>>>>>>>>>>>>>>>> DataInitializer");
        if (customerRepository.count() > 1) {
            log.debug("Database already initialized, skipping data ingestion");
            return;
        }
        log.debug("Ingesting data into the database");
        ingestTermOfServiceToVectorStore();
        List<Customer> customers = List.of(
                new Customer(null, "John", "Doe", null),
                new Customer(null, "Jane", "Smith", null),
                new Customer(null, "Michael", "Johnson", null),
                new Customer(null, "Sarah", "Williams", null),
                new Customer(null, "Robert", "Taylor", null)
        );

        customerRepository.saveAll(customers);

        List<Booking> bookings = List.of(
                new Booking(null, "101", LocalDate.now(), customers.get(0), "LAX", "SFO", BookingStatus.CONFIRMED, BookingClass.ECONOMY),
                new Booking(null, "102", LocalDate.of(2025, 6, 3), customers.get(1), "JFK", "LHR", BookingStatus.CONFIRMED, BookingClass.BUSINESS),
                new Booking(null, "103", LocalDate.of(2025, 6, 5), customers.get(2), "CDG", "MUC", BookingStatus.CONFIRMED, BookingClass.PREMIUM_ECONOMY),
                new Booking(null, "104", LocalDate.of(2025, 6, 7), customers.get(3), "ARN", "FRA", BookingStatus.CONFIRMED, BookingClass.ECONOMY),
                new Booking(null, "105", LocalDate.of(2025, 6, 9), customers.get(4), "HEL", "TXL", BookingStatus.CONFIRMED, BookingClass.BUSINESS)
        );

        bookingRepository.saveAll(bookings);
    }
}