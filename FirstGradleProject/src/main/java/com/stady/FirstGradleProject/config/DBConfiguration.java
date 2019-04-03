package com.stady.FirstGradleProject.config;

import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

    private  String driverClassName;
    @Length(min = 10)
    private  String url;
    @NotBlank
    private  String username;
    private  String password;

    @Autowired
    private Logger logger;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnectionDetails(){
        logger.debug("DB connection for TEST - H2");
        logger.debug(driverClassName);
        logger.debug(url);
        return "DB connection for TEST - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnectionDetails(){
        logger.debug("DB connection for PROD - MYSQL");
        logger.debug(driverClassName);
        logger.debug(url);
        return "DB connection for PROD - MYSQL";
    }
}
