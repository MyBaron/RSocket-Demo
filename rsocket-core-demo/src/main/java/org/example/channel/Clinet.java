package org.example.channel;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

/**
 * @author: baron
 * @date: 2020-06-14 13:32
 **/
public class Clinet {

    public static void main(String[] args) {
        RSocket socket = RSocketConnector.create()
                .connect(TcpClientTransport.create("localhost", 7000))
                .block();

        socket.requestChannel(Flux.just("hello", "world", "goodbye").map(DefaultPayload::create))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .blockLast();

        socket.dispose();

    }
}
