package com.stady.FirstGradleProject.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@Api(value = "UserController", description = "This Resource is used for internationalization example")
public class HelloController {

    @Autowired
    MessageSource messageSource;

//    @GetMapping(path = "/hello_world")
//    public String helloWorld(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale){
//        return messageSource.getMessage("good.morning.message",null, locale);
//    }

    @GetMapping(path = "/hello_world")
    public String helloWorld(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
