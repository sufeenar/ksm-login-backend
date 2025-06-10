package com.example.ksm_login_backend.config;


import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    

        @Configuration
        public class AppConfig {
            @Bean
            public ModelMapper modelMapper() {
                return new ModelMapper();
            }
        }
        
}
