package com.rayllanderson.cars.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rayllanderson.cars.api.util.StringToEnumConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
	registry.addConverter(new StringToEnumConverter());
    }
}
