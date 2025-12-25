package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.servlet.HealthServlet;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ServletRegistrationBean<HealthServlet> healthServletRegistration() {
        ServletRegistrationBean<HealthServlet> registration = new ServletRegistrationBean<>(new HealthServlet());
        registration.addUrlMappings("/health");
        return registration;
    }
}