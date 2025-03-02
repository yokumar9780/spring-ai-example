package com.ns.chatbot.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Response object from the OpenWeather API.
 * <p>
 * This class maps the JSON response from the OpenWeather API to Java objects.
 * It includes:
 * <ul>
 *   <li>Geographic coordinates</li>
 *   <li>Current weather conditions</li>
 *   <li>Temperature and atmospheric details</li>
 *   <li>Wind, clouds, and visibility information</li>
 *   <li>Location metadata (timezone, name, etc.)</li>
 * </ul>
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse implements Serializable {
    /**
     * Geographic coordinates of the location.
     */
    private Coord coord;
    
    /**
     * List of weather conditions (may include multiple conditions).
     */
    private List<Weather> weather;
    
    /**
     * Internal parameter.
     */
    private String base;
    
    /**
     * Main weather parameters (temperature, pressure, etc.).
     */
    private Main main;
    
    /**
     * Visibility in meters.
     */
    private int visibility;
    
    /**
     * Wind information (speed, direction).
     */
    private Wind wind;
    
    /**
     * Cloud coverage information.
     */
    private Clouds clouds;
    
    /**
     * Time of data calculation, unix, UTC.
     */
    private long dt;
    
    /**
     * System parameters (country, sunrise/sunset).
     */
    private Sys sys;
    
    /**
     * Timezone shift from UTC in seconds.
     */
    private int timezone;
    
    /**
     * City ID.
     */
    private int id;
    
    /**
     * City name.
     */
    private String name;
    
    /**
     * Internal parameter.
     */
    private int cod;
}

/**
 * Geographic coordinates.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Coord implements Serializable {
    /**
     * Longitude coordinate.
     */
    private double lon;
    
    /**
     * Latitude coordinate.
     */
    private double lat;
}

/**
 * Weather condition information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Weather implements Serializable {
    /**
     * Weather condition ID.
     */
    private int id;
    
    /**
     * Group of weather parameters (Rain, Snow, Clouds etc.).
     */
    private String main;
    
    /**
     * Weather condition within the group.
     */
    private String description;
    
    /**
     * Weather icon ID.
     */
    private String icon;
}

/**
 * Main weather parameters.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Main implements Serializable {
    /**
     * Temperature in the requested units.
     */
    private double temp;
    
    /**
     * Human perception of temperature.
     */
    @JsonProperty("feels_like")
    private double feelsLike;
    
    /**
     * Minimum temperature.
     */
    @JsonProperty("temp_min")
    private double tempMin;
    
    /**
     * Maximum temperature.
     */
    @JsonProperty("temp_max")
    private double tempMax;
    
    /**
     * Atmospheric pressure in hPa.
     */
    private int pressure;
    
    /**
     * Humidity percentage.
     */
    private int humidity;
    
    /**
     * Atmospheric pressure at sea level.
     */
    @JsonProperty("sea_level")
    private int seaLevel;
    
    /**
     * Atmospheric pressure at ground level.
     */
    @JsonProperty("grnd_level")
    private int grndLevel;
}

/**
 * Wind information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Wind implements Serializable {
    /**
     * Wind speed in the requested units.
     */
    private double speed;
    
    /**
     * Wind direction in degrees (meteorological).
     */
    private int deg;
}

/**
 * Cloud coverage information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Clouds implements Serializable {
    /**
     * Cloudiness percentage.
     */
    private int all;
}

/**
 * System parameters.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Sys implements Serializable {
    /**
     * Internal parameter.
     */
    private int type;
    
    /**
     * Internal parameter.
     */
    private int id;
    
    /**
     * Country code.
     */
    private String country;
    
    /**
     * Sunrise time, unix, UTC.
     */
    private long sunrise;
    
    /**
     * Sunset time, unix, UTC.
     */
    private long sunset;
}
