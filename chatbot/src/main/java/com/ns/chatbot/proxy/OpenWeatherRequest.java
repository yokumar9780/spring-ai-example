package com.ns.chatbot.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Example:
 * <p>
 * double latitude = 37.7749;  // Example: San Francisco
 * double longitude = -122.4194;
 * String units = "metric"; // Options: standard, metric, imperial
 * String exclude = "minutely,hourly"; // Optional: Data to exclude
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherRequest {
    private double latitude;
    private double longitude;
    @Builder.Default
    private String units = "metric";
    @Builder.Default
    private String exclude = "minutely,hourly";

}
