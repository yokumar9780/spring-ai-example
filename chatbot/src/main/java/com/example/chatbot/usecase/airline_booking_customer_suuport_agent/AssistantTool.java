package com.example.chatbot.usecase.airline_booking_customer_suuport_agent;

import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.Booking;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.BookingDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class AssistantTool {

    private final Map<String, Booking> bookings = new HashMap<>();
    private final VectorStore vectorStore;
    private final ObjectMapper mapper;
    private final AssistantService assistantService;

    @Tool(description = "Get all bookings")
    public List<BookingDetails> getAllBookings() {
        try {
            return assistantService.getBookings();
        } catch (Exception e) {
            //log.error("No Bookings details: {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
            return Collections.emptyList();
        }
    }

    @Tool(description = "Get booking details")
    public BookingDetails getBooking(String bookingNumber, String firstName, String lastName) {
        try {
            return assistantService.getBookingDetails(bookingNumber, firstName, lastName);
        } catch (Exception e) {
            //log.warn("Booking details: {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
            return new BookingDetails(bookingNumber, firstName, lastName, null, null,
                    null, null, null);
        }
    }

    @Tool(description = "Change booking dates")
    public void changeBooking(String bookingNumber, String firstName, String lastName, String newDate, String from,
                              String to) {
        assistantService.changeBooking(bookingNumber, firstName, lastName, newDate, from, to);
    }

    ;

    @Tool(description = "Cancel booking")
    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        assistantService.cancelBooking(bookingNumber, firstName, lastName);
    }
}
