package com.ns.chatbot.config.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * Configuration for system-wide HTTP proxy settings.
 * <p>
 * This class:
 * <ul>
 *   <li>Sets system properties for HTTP and HTTPS proxies</li>
 *   <li>Configures a RestTemplate that uses the proxy</li>
 *   <li>Sets up proxy authentication if credentials are provided</li>
 * </ul>
 * <p>
 * This configuration is only active in the "dev" profile.
 *
 * @author Spring AI Workshop Team
 * @version 1.0
 */
@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class SystemProxyConfig implements InitializingBean {
    /**
     * The proxy settings from application properties.
     */
    private final ProxySettings proxy;

    /**
     * Sets system properties for HTTP and HTTPS proxies after bean initialization.
     */
    @Override
    public void afterPropertiesSet() {
        System.setProperty("http.proxyHost", proxy.host());
        System.setProperty("http.proxyPort", Integer.toString(proxy.port()));

        System.setProperty("https.proxyHost", proxy.host());
        System.setProperty("https.proxyPort", Integer.toString(proxy.port()));
    }

    /**
     * Creates a RestTemplate configured to use the proxy.
     * <p>
     * This bean is marked as @Primary to override any other RestTemplate
     * beans when in the dev profile.
     *
     * @return A RestTemplate that routes requests through the configured proxy
     */
    @Bean
    @Primary
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.host(), proxy.port())));
        // Set proxy authentication if username and password are provided
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == RequestorType.PROXY) {
                    return new PasswordAuthentication(proxy.username(), proxy.password().toCharArray());
                } else {
                    return super.getPasswordAuthentication();
                }
            }
        });

        return new RestTemplate(requestFactory);
    }
}
