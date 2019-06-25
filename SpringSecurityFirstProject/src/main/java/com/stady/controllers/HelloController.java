package com.stady.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("ssec")
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(){
//        return "Hello World!";
        return "hello.html";
    }
}
