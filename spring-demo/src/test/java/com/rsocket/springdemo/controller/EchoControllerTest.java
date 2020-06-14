package com.rsocket.springdemo.controller;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EchoControllerTest extends AbstractTest{




    @Test
    @DisplayName("测试客户端")
    public void testEcho() {
        RSocketRequester rSocketRequester = createRSocketRequester();

        /**
         *  route() 指定消息目的地
         */
        Mono<String> response = rSocketRequester.route("echo")
                .data("hello")
                .retrieveMono(String.class);

        StepVerifier.create(response)
                .expectNext("ECHO >>hello")
                .expectComplete()
                .verify();


    }




}