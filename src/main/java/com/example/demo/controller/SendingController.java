package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SendingController {

    private final WebClient webClient;

    @Autowired
    public SendingController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        return webClient.post()
                .uri("/respond")
                .bodyValue(message)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
