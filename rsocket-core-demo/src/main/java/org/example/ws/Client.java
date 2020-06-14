package org.example.ws;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import io.rsocket.util.DefaultPayload;

/**
 * @author: baron
 * @date: 2020-06-14 16:20
 **/
public class Client {

    public static void main(String[] args) {
        RSocket rSocket = RSocketConnector.create()
                .connect(WebsocketClientTransport.create("localhost", 7000))
                .block();

        rSocket.requestResponse(DefaultPayload.create("hello"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .then()
                .doFinally(k->rSocket.dispose())
                .block();


    }
}
