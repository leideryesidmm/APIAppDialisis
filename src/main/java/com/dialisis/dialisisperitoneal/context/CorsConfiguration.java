package com.dialisis.dialisisperitoneal.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
<<<<<<< HEAD
                //Se pone el primer puerto que haya en el frontend en el docker-compose XD
                .allowedOrigins("http://localhost:8103")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("Content-Type", "Accept")
=======
                .allowedOrigins("http://localhost:8103")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("*")
>>>>>>> 75d3c35f43080621a2c9aa4c33d31570ebafed00
                .allowCredentials(false)
                .maxAge(3600);
    }
}