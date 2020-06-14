package org.example.stream;

import io.rsocket.Payload;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author: baron
 * @date: 2020-06-13 17:42
 **/
public class Server {


    public static void main(String[] args) throws InterruptedException {

        RSocketServer.create(
                SocketAcceptor.forRequestStream(
                        payload -> {
                            Flux<Payload> map = Flux.interval(Duration.ofMillis(100))
                                    .map(aLong -> DefaultPayload.create("Interval:" + aLong));

                            System.out.println(payload.getDataUtf8());
                            return map;
                        }



                )
        ).bind(TcpServerTransport.create("localhost", 7000)).subscribe();


        Thread.sleep(1000000);
    }


}
