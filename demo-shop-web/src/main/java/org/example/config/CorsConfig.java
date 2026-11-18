package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/demo/**")
                .allowedOriginPatterns(
                        "http://localhost",
                        "http://localhost:[*]",
                        "http://127.0.0.1",
                        "http://127.0.0.1:[*]",
                        "http://10.212.134.108",
                        "http://10.212.134.108:[*]",
                        "http://10.130.12.124",
                        "http://10.130.12.124:[*]",
                        "http://172.24.192.1",
                        "http://172.24.192.1:[*]"
                        )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
