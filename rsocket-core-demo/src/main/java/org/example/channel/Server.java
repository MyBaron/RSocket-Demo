package org.example.channel;

import io.rsocket.Payload;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

/**
 * @author: baron
 * @date: 2020-06-14 13:32
 **/
public class Server {


    public static void main(String[] args) throws InterruptedException {
        RSocketServer.create(
                SocketAcceptor.forRequestChannel(
                        payloadPublisher ->
                                Flux.from(payloadPublisher)
                                        .flatMap(payload ->
                                                Flux.fromStream(payload.getDataUtf8().codePoints()
                                                        .mapToObj(c -> String.valueOf((char) c))
                                                        .map(DefaultPayload::create))
                                        )
                )
        ).bind(TcpServerTransport.create("localhost", 7000))
                .subscribe();

        Thread.sleep(1000000);
    }
}
