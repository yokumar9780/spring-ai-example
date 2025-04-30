package com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record BookingDetails(String bookingNumber,
                             String firstName,
                             String lastName,
                             LocalDate date,
                             BookingStatus bookingStatus,
                             String from,
                             String to,
                             String bookingClass) {
}
