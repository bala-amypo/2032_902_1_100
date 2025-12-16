package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestControllerpublic 
public class Basic{
    @GetMapping("/home")
    public String sample(){
        return "hello world";
    }
}