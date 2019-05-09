package com.stady.FirstGradleProject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({SwaggerConfig.class})
@ComponentScan(basePackages = "config, controllers, services, repository")
public class ProjectConfig {

    @Bean
    @Scope("prototype")
    Logger logger(){
        return LoggerFactory.getLogger("custom-logger");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
