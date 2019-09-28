package com.stady.FirstGradleProject.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "HelloController", description = "This Resource is used for internationalization example")
public class HelloController {

    @Autowired
    MessageSource messageSource;

    @Value("${app.message}")
    private String testMessage;

//    @GetMapping(path = "/hello_world")
//    public String helloWorld(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) Locale locale){
//        return messageSource.getMessage("good.morning.message",null, locale);
//    }

    @GetMapping(path = "/hello")
    @ApiOperation(value = "Return the hello message in internationalization form")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation.")
            }
    )
    public String helloMessage(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Return the environment message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation, the application is up!")
            }
    )
    public String getEnvironmentMessage() {
        return testMessage;
    }
}
