package com.ns.chatbot.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class OpenWeatherResponse implements Serializable {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Coord implements Serializable {
    private double lon;
    private double lat;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Weather implements Serializable {
    private int id;
    private String main;
    private String description;
    private String icon;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Main implements Serializable {
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    private int pressure;
    private int humidity;
    @JsonProperty("sea_level")
    private int seaLevel;
    @JsonProperty("grnd_level")
    private int grndLevel;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Wind implements Serializable {
    private double speed;
    private int deg;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Clouds implements Serializable {
    private int all;

    // Getters and Setters
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Sys implements Serializable {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;

    // Getters and Setters
}
