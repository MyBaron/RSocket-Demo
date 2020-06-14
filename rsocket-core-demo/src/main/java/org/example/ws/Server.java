package org.example.ws;

import io.rsocket.AbstractRSocket;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.WebsocketServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

/**
 * @author: baron
 * @date: 2020-06-14 16:20
 **/
public class Server {

    public static void main(String[] args) throws InterruptedException {
        RSocketServer.create(
                SocketAcceptor.forRequestResponse(
                        payload ->
                                Mono.just(
                                        DefaultPayload.create("ECHO >>" + payload.getDataUtf8())
                                )
                )
        ).bind(WebsocketServerTransport.create("localhost", 7000))
                .subscribe();

        Thread.sleep(100000);
    }
}
