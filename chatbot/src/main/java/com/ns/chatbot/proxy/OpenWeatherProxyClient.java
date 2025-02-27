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

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenWeatherProxyClient implements Function<OpenWeatherRequest, OpenWeatherResponse> {
    @Value("${openWeather.url}")
    private String openWeatherUrl;

    @Value("${openWeather.apiKey}")
    private String openWeatherApiKey;

    private final RestTemplate restTemplate;

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

