package com.rsocket.springdemo.controller;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * @author: baron
 * @date: 2020-06-14 16:11
 **/

public abstract class AbstractTest {

    @Value("${spring.rsocket.server.port}")
    private int serverPort;

    @Autowired
    private RSocketRequester.Builder builder;

    @Test
    public RSocketRequester createRSocketRequester() {
        RSocketRequester requester = builder.dataMimeType(MimeTypeUtils.TEXT_PLAIN)
                .connect(TcpClientTransport.create(serverPort)).block();
        return requester;
    }

    public RSocketRequester createRSocketRequesterJson(){
        return builder.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .connect(TcpClientTransport.create(serverPort)).block();
    }

}
