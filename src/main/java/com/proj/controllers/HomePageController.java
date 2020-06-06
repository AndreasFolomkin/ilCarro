package com.proj.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomePageController {

    @GetMapping("/")
    public String getHello(){
        return "Hello";
    }
}
