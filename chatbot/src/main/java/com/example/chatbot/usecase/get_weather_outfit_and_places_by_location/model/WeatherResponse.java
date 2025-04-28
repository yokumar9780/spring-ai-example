package com.example.chatbot.usecase.get_weather_outfit_and_places_by_location.model;


import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record WeatherResponse(
        WeatherDetails weatherDetails,
        SuggestedOutfit suggestedOutfit,
        List<PlaceToVisit> placesToVisit) implements Serializable {
}


@Builder
record WeatherDetails(
        String description,
        double temperatureCelsius,
        double temperatureFahrenheit,
        int humidity) implements Serializable {
}

@Builder
record SuggestedOutfit(
        String clothing,
        String shoes,
        String accessories) implements Serializable {
}

@Builder
record PlaceToVisit(
        String name,
        String description) implements Serializable {
}