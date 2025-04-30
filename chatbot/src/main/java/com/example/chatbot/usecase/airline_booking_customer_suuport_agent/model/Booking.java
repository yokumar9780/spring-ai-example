package com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingNumber;
    private LocalDate date;
    @ManyToOne
    private Customer customer;
    @Column(name = "from_airport")
    private String fromAirport;
    @Column(name = "to_airport")
    private String toAirport;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    @Enumerated(EnumType.STRING)
    private BookingClass bookingClass;
}
