package com.ns.chatbot.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for the OpenWeather API.
 * <p>
 * This class encapsulates the parameters needed for an OpenWeather API request:
 * <ul>
 *   <li>Geographic coordinates (latitude and longitude)</li>
 *   <li>Unit system for temperature and measurements</li>
 *   <li>Data parts to exclude from the response</li>
 * </ul>
 * <p>
 * Example:
 * <pre>
 * double latitude = 37.7749;  // Example: San Francisco
 * double longitude = -122.4194;
 * String units = "metric"; // Options: standard, metric, imperial
 * String exclude = "minutely,hourly"; // Optional: Data to exclude
 * </pre>
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherRequest {
    /**
     * The latitude coordinate of the location.
     */
    private double latitude;

    /**
     * The longitude coordinate of the location.
     */
    private double longitude;

    /**
     * The unit system for measurements (standard, metric, or imperial).
     * Defaults to "metric" (Celsius, meters/sec, etc.).
     */
    @Builder.Default
    private String units = "metric";

    /**
     * Data parts to exclude from the response.
     * Defaults to "minutely,hourly" to reduce response size.
     */
    @Builder.Default
    private String exclude = "minutely,hourly";
}
