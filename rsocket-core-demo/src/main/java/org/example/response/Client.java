package org.example.response;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

/**
 * @author: baron
 * @date: 2020-06-13 23:10
 **/
public class Client {

    public static void main(String[] args) {
        RSocket socket = RSocketConnector.create()
                .payloadDecoder(PayloadDecoder.ZERO_COPY)
                .connect(TcpClientTransport.create("localhost", 7000))
                .block();

        socket.requestResponse(DefaultPayload.create("hello"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .then()
                .doFinally(signalType -> socket.dispose())
                .then()
                .block();


    }
}
