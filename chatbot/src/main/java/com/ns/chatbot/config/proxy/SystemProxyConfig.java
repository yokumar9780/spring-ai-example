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

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class SystemProxyConfig implements InitializingBean {
    private final ProxySettings proxy;

    @Override
    public void afterPropertiesSet() {
        System.setProperty("http.proxyHost", proxy.host());
        System.setProperty("http.proxyPort", Integer.toString(proxy.port()));

        System.setProperty("https.proxyHost", proxy.host());
        System.setProperty("https.proxyPort", Integer.toString(proxy.port()));
    }


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
