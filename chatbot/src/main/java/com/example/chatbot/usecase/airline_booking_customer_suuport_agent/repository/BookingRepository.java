package com.example.chatbot.usecase.airline_booking_customer_suuport_agent.repository;

import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Optional<Booking> findByBookingNumberAndCustomerFirstNameAndCustomerLastName(String bookingNumber, String firstName, String lastName);
}