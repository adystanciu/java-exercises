package com.stady.FirstGradleProject.config;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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

    @Bean
    public LocaleResolver localResolver(){
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);

        return localeResolver;
    }

}
