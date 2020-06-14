package com.rsocket.springdemo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * @author: baron
 * @date: 2020-06-14 14:50
 **/
@Controller
public class EchoController {


    @MessageMapping("echo")
    public Mono<String> echo(String input) {
        return Mono.just("ECHO >>" + input);
    }
}
