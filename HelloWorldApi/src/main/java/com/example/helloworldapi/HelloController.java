package com.example.helloworldapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello, World";
    }
    @GetMapping("/pessoa")
    public Pessoa pessoa(){
        return new Pessoa("Samira",16);
    }
}
