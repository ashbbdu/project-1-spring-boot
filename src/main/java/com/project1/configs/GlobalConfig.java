package com.project1.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
    @Bean
    public ModelMapper getModelMapper () {
        return new ModelMapper();
    }
}
