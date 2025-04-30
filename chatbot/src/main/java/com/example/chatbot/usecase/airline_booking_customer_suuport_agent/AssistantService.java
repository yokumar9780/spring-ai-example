package com.example.chatbot.usecase.airline_booking_customer_suuport_agent;

import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.Booking;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.BookingDetails;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.BookingStatus;
import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistantService {

    private final BookingRepository bookingRepository;


    public List<BookingDetails> getBookings() {
        List<BookingDetails> bookingDetails = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        bookings.forEach(booking -> {
            bookingDetails.add(toBookingDetails(booking));
        });
        return bookingDetails;
    }

    public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {
        return bookingRepository.findByBookingNumberAndCustomerFirstNameAndCustomerLastName(bookingNumber, firstName, lastName)
                .map(this::toBookingDetails)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }

    public void changeBooking(String bookingNumber, String firstName, String lastName, String newDate, String from, String to) {
        bookingRepository.findByBookingNumberAndCustomerFirstNameAndCustomerLastName(bookingNumber, firstName, lastName)
                .ifPresent(booking -> {
                    if (booking.getDate().isBefore(LocalDate.now().plusDays(1))) {
                        throw new IllegalArgumentException("Booking cannot be changed within 24 hours of the start date.");
                    }
                    booking.setDate(LocalDate.parse(newDate));
                    booking.setFromAirport(from);
                    booking.setToAirport(to);
                    bookingRepository.save(booking);
                });

    }

    public void cancelBooking(String bookingNumber, String firstName, String lastName) {
        bookingRepository.findByBookingNumberAndCustomerFirstNameAndCustomerLastName(bookingNumber, firstName, lastName)
                .ifPresent(booking -> {
                    if (booking.getDate().isBefore(LocalDate.now().plusDays(2))) {
                        throw new IllegalArgumentException("Booking cannot be cancelled within 48 hours of the start date.");
                    }
                    booking.setBookingStatus(BookingStatus.CANCELLED);
                    bookingRepository.save(booking);
                });

    }


    private BookingDetails toBookingDetails(Booking booking) {
        return new BookingDetails(
                booking.getBookingNumber(),
                booking.getCustomer().getFirstName(),
                booking.getCustomer().getLastName(),
                booking.getDate(),
                booking.getBookingStatus(),
                booking.getFromAirport(),
                booking.getToAirport(),
                booking.getBookingClass().toString()
        );
    }
}
