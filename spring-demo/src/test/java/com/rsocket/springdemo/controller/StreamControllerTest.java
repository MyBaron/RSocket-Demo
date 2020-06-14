package com.rsocket.springdemo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StreamControllerTest  extends AbstractTest{

    @Test
    @DisplayName("测试请求-响应流")
    void stringSplit() {
        RSocketRequester rSocketRequester = createRSocketRequester();
        Flux<String> stringFlux = rSocketRequester.route("stringSplit")
                .data("hello")
                .retrieveFlux(String.class);

        StepVerifier.create(stringFlux)
                .expectNext("h", "e", "l", "l", "o")
                .expectComplete()
                .verify();


    }
}