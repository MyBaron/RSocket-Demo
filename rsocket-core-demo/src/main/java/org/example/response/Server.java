package org.example.response;

import io.rsocket.Payload;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

/**
 * @author: baron
 * @date: 2020-06-13 23:10
 **/
public class Server {

    public static void main(String[] args) throws InterruptedException {
        RSocketServer.create(
                SocketAcceptor.forRequestResponse(
                        payload -> {
                            Mono<Payload> mono = Mono.just(DefaultPayload.create("ECHO>>" + payload.getDataUtf8()));

                            System.out.println(payload.getDataUtf8());
                            return mono;
                        }
                )
        ).bind(TcpServerTransport.create("localhost", 7000))
                .subscribe();

        Thread.sleep(100000);
    }
}
