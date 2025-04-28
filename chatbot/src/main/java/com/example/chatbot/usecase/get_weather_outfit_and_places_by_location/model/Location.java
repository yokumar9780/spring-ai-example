package com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.model;

import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record Location(@NotNull double latitude, @NotNull double longitude) {
}