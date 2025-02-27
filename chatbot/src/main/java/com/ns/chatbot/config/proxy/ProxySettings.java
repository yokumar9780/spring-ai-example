package com.ns.chatbot.config.proxy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "proxy")
public record ProxySettings(
        String host,
        @DefaultValue("80")
        int port,
        String username,
        String password
) {
}
