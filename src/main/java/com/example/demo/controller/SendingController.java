package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SendingController {

    private final WebClient webClient;

    @Autowired
    public SendingController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        if (message == null || message.isBlank()) {
            return "Please, input message!";
        }
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/respond")
                        .queryParam("message", message)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
