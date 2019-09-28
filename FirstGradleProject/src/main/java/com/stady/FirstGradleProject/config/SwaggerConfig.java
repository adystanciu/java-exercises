package com.stady.FirstGradleProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json"));//                    "application/xml"));

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaInfo())
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                // if I commented these below rows will show also the CREExceptionHandlerController
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stady.FirstGradleProject"))
                .build();
//                .paths(PathSelectors.regex("/rest.*"));
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "FirstGradleProject Swagger API",
                "FirstGradleProject Swagger API Documentation",
                "1.0",
                "Terms of Service",
                new Contact("Adi Stanciu", "https://github.com/adystanciu","ady_stanciu_91@yahoo.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
                new ArrayList<>() //vendor extension
        );

        return apiInfo;
    }
}
