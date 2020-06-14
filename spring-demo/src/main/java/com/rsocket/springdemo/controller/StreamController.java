package com.rsocket.springdemo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

/**
 * @author: baron
 * @date: 2020-06-14 16:08
 *
 *  请求-响应式流模式
 **/
@Controller
public class StreamController {


    @MessageMapping("stringSplit")
    public Flux<String> stringSplit(String input){
        return Flux.fromStream(input.codePoints().mapToObj(c -> String.valueOf((char) c)));
    }

}
