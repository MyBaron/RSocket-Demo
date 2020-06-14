package com.rsocket.springdemo.controller;

import com.rsocket.springdemo.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * @author: baron
 * @date: 2020-06-14 21:04
 *
 *  Message对象传输
 **/
@Controller
@Slf4j
public class ObjectController {


    @MessageMapping("request-response")
    public Message requestResponse(Message request){
      log.info("request:{}",request);
        Message message = new Message("SERVER","RESPONSE");
        return message;
    }
}
