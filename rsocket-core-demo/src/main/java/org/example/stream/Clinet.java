package org.example.stream;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author: baron
 * @date: 2020-06-13 17:36
 **/
public class Clinet {

    private static final Logger logger = LoggerFactory.getLogger(Clinet.class);


    public static void main(String[] args) {
        RSocket clientRSocket = RSocketConnector.create()
                .payloadDecoder(PayloadDecoder.ZERO_COPY)
                .connect(TcpClientTransport.create("localhost",7000))
                .block();


        clientRSocket.requestStream(DefaultPayload.create("hello"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .take(10)
                .then()
                .doFinally(signalType -> clientRSocket.dispose())
                .then()
                .block();

    }

}
