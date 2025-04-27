package com.ns.chatbot.proxy;

import com.ns.chatbot.utils.PrintJsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Function;

/**
 * A proxy client for the OpenWeather API that provides weather information.
 * <p>
 * This service handles communication with the OpenWeather API, including:
 * <ul>
 *   <li>Building API requests with proper parameters</li>
 *   <li>Handling API responses and error conditions</li>
 *   <li>Converting external API data to internal model objects</li>
 * </ul>
 * <p>
 * The client implements the Function interface to support functional programming
 * patterns and can be used with the Spring Cloud Function framework.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OpenWeatherProxyClient implements Function<OpenWeatherRequest, OpenWeatherResponse> {
    /**
     * The RestTemplate used for making HTTP requests to the OpenWeather API.
     */
    private final RestTemplate restTemplate;
    /**
     * The base URL for the OpenWeather API, injected from application properties.
     */
    @Value("${openWeather.url}")
    private String openWeatherUrl;
    /**
     * The API key for authenticating with the OpenWeather API, injected from application properties.
     */
    @Value("${openWeather.apiKey}")
    private String openWeatherApiKey;

    /**
     * Applies this function to the given request to retrieve weather data.
     * <p>
     * This method builds a request to the OpenWeather API using the provided
     * latitude and longitude, makes the API call, and processes the response.
     *
     * @param request The OpenWeatherRequest containing latitude and longitude
     * @return The OpenWeatherResponse with weather data
     * @throws RuntimeException if there is an error fetching the weather data
     */
    @Override
    public OpenWeatherResponse apply(OpenWeatherRequest request) {
        String url = UriComponentsBuilder.fromUriString(openWeatherUrl)
                .queryParam("lat", request.getLatitude())
                .queryParam("lon", request.getLongitude())
                .queryParam("appid", openWeatherApiKey)
                .toUriString();
        ResponseEntity<OpenWeatherResponse> response = restTemplate.getForEntity(url, OpenWeatherResponse.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            OpenWeatherResponse responseBody = response.getBody();
            PrintJsonUtil.log(responseBody);
            return responseBody;
        } else {
            log.error("Error fetching weather data: {}", response.getStatusCode());
            throw new RuntimeException("Error fetching weather data: " + response.getStatusCode());
        }
    }
}

