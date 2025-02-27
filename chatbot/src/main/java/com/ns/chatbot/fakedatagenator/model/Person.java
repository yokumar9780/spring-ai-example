package com.ns.chatbot.fakedatagenator.model;

import lombok.Builder;

@Builder
public record Person(Integer id, String firstName, String lastName,
                     int age, Gender gender, String nationality) {

}

