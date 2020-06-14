package org.example.fireandforget;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

/**
 * @author: baron
 * @date: 2020-06-14 11:38
 **/
public class Client {

    public static void main(String[] args) throws InterruptedException {
        RSocket rSocket = RSocketConnector.create()
                .connect(TcpClientTransport.create("localhost", 7000))
                .block();


        rSocket.fireAndForget(DefaultPayload.create("hello"))
                .then()
                .log()
                .block();

        rSocket.fireAndForget(DefaultPayload.create("world"))
                .then()
                .doFinally(signalType -> rSocket.dispose())
                .block();
        System.out.println("关闭");

    }
}
