package com.stady.FirstGradleProject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

    private  String driverClassName;
    private  String url;
    private  String username;
    private  String password;
    private String message;

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
        System.out.println("DB connection for TEST - H2");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for TEST - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnectionDetails(){
        System.out.println("DB connection for PROD - Mysql");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for Prod - Mysql";
    }
}
