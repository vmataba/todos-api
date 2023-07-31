package com.tabaapps.todos.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Cors {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // or specific origins
        config.addAllowedMethod("*"); // or specific HTTP methods
        config.addAllowedHeader("*"); // or specific headers
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
