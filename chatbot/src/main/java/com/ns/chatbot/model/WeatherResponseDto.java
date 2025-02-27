package com.ns.chatbot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponseDto implements Serializable {

    private WeatherDetails weatherDetails;
    private SuggestedOutfit suggestedOutfit;
    private List<PlaceToVisit> placesToVisit;


    // Getters and Setters for main DTO fields
}

// Getters and Setters
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class WeatherDetails implements Serializable {
    private String description;
    private double temperatureCelsius;
    private double temperatureFahrenheit;
    private int humidity;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class SuggestedOutfit implements Serializable {
    private String clothing;
    private String shoes;
    private String accessories;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class PlaceToVisit implements Serializable {
    private String name;
    private String description;

    // Getters and Setters
}