package com.study.gateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@ConfigurationProperties("study.zuul.token-filter")
public class TokenConfiguration {
    public List<String> noAuthenticationRoutes;

    public List<String> getNoAuthenticationRoutes() {
        return noAuthenticationRoutes;
    }

    public void setNoAuthenticationRoutes(List<String> noAuthenticationRoutes) {
        this.noAuthenticationRoutes = noAuthenticationRoutes;
    }
}
