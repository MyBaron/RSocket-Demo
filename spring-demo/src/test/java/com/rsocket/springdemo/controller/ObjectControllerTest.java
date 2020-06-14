package com.rsocket.springdemo.controller;

import com.rsocket.springdemo.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ObjectControllerTest extends AbstractTest {

    @Test
    void requestResponse() {
        RSocketRequester rSocketRequester = createRSocketRequesterJson();


        Message message = new Message("CLIENT", "REQUEST");
        Mono<Message> messageMono = rSocketRequester.route("request-response")
                .data(message)
                .retrieveMono(Message.class);

        StepVerifier.create(messageMono)
                .consumeNextWith(System.out::println)
                .verifyComplete();

    }
}