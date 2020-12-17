package io.rbetik12.eengine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/home")
public class Home {
    @GetMapping("*")
    public String hello() {
        return "Hello, world!";
    }
}
