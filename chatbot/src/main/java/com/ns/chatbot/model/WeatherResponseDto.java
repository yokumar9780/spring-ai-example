package com.ns.chatbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Data transfer object for weather information with recommendations.
 * <p>
 * This class combines:
 * <ul>
 *   <li>Weather details (temperature, humidity, etc.)</li>
 *   <li>Clothing recommendations based on the weather</li>
 *   <li>Suggested places to visit given the conditions</li>
 * </ul>
 * <p>
 * It implements Serializable to support data transfer and caching.
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponseDto implements Serializable {
    /**
     * Detailed weather information.
     */
    private WeatherDetails weatherDetails;
    
    /**
     * Clothing recommendations based on the weather.
     */
    private SuggestedOutfit suggestedOutfit;
    
    /**
     * Places recommended to visit given the weather conditions.
     */
    private List<PlaceToVisit> placesToVisit;
}

/**
 * Contains detailed weather information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class WeatherDetails implements Serializable {
    /**
     * Text description of the weather (e.g., "Partly Cloudy").
     */
    private String description;
    
    /**
     * Temperature in Celsius.
     */
    private double temperatureCelsius;
    
    /**
     * Temperature in Fahrenheit.
     */
    private double temperatureFahrenheit;
    
    /**
     * Humidity percentage.
     */
    private int humidity;
}

/**
 * Contains clothing recommendations based on weather.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class SuggestedOutfit implements Serializable {
    /**
     * Recommended clothing items (e.g., "Light jacket").
     */
    private String clothing;
    
    /**
     * Recommended footwear (e.g., "Waterproof boots").
     */
    private String shoes;
    
    /**
     * Recommended accessories (e.g., "Umbrella, sunglasses").
     */
    private String accessories;
}

/**
 * Represents a place recommended to visit.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class PlaceToVisit implements Serializable {
    /**
     * Name of the place.
     */
    private String name;
    
    /**
     * Description of the place and why it's suitable.
     */
    private String description;
}