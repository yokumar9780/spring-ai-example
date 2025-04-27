package com.ns.chatbot.model;

import lombok.Builder;

import javax.validation.constraints.NotNull;

/**
 * Represents a geographic location with latitude and longitude coordinates.
 * <p>
 * This record is used for specifying locations in weather queries.
 * Both latitude and longitude are required fields.
 *
 * @param latitude  The latitude coordinate (required)
 * @param longitude The longitude coordinate (required)
 */
@Builder
public record Location(@NotNull double latitude, @NotNull double longitude) {
}

