package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class RespondingController {

    @GetMapping("/respond")
    public String respond(@RequestParam String message) {
        return message;
    }
}
