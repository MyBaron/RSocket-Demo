package org.example.fireandforget;

import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.publisher.Mono;

/**
 * @author: baron
 * @date: 2020-06-14 11:38
 **/
public class Server {


    public static void main(String[] args) throws InterruptedException {
        RSocketServer.create(
                SocketAcceptor.forFireAndForget(
                        payload -> {
                            System.out.println("receive: " + payload.getDataUtf8());
                            return Mono.empty();
                        }
                )
        ).bind(TcpServerTransport.create("localhost", 7000))
                .subscribe();


        Thread.sleep(100000);

    }

}
