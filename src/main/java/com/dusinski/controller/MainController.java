package com.dusinski.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(path = "/api/hello")
    public String sayHello() {
        return "Welcome in holiday booking app";
    }
}
