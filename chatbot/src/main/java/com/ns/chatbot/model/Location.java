package com.ns.chatbot.model;

import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record Location(@NotNull double latitude, @NotNull double longitude) {
}

