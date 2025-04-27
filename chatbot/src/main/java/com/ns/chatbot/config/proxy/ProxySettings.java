package com.ns.chatbot.config.proxy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Configuration properties for HTTP proxy settings.
 * <p>
 * This record captures proxy configuration from application properties
 * with the "proxy" prefix, allowing the application to connect through
 * corporate or development proxies.
 *
 * @param host     The hostname or IP address of the proxy server
 * @param port     The port number of the proxy server (defaults to 80)
 * @param username The username for proxy authentication (if required)
 * @param password The password for proxy authentication (if required)
 */
@ConfigurationProperties(prefix = "proxy")
public record ProxySettings(
        String host,
        @DefaultValue("80")
        int port,
        String username,
        String password
) {
}
