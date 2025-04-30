package com.example.chatbot.usecase.airline_booking_customer_suuport_agent.repository;


import com.example.chatbot.usecase.airline_booking_customer_suuport_agent.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstNameAndLastName(String firstName, String lastName);
}