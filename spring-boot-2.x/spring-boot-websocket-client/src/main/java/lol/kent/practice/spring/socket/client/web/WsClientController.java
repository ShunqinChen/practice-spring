package lol.kent.practice.spring.socket.client.web;

import java.util.concurrent.ExecutionException;
import lol.kent.practice.spring.socket.client.handler.CustomStompSessionHandler;
import lol.kent.practice.spring.socket.client.handler.SubscribeHandler;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年06月17日 14:04
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("messages")
public class WsClientController {

    private final WebSocketStompClient socketStompClient;

    public WsClientController(WebSocketStompClient socketStompClient) {
        this.socketStompClient = socketStompClient;
    }

    @PostMapping
    public void send() {
        StompSessionHandler sessionHandler = new CustomStompSessionHandler();
        String url = "ws://192.168.1.4:8080/gs-guide-websocket";
        StompSession session;
        try {
            session = socketStompClient.connect(url, sessionHandler).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return;
        }
        session.send("/app/hello", "Kent");

        SubscribeHandler subscribeHandler = new SubscribeHandler();
        session.subscribe("/topic/messages", subscribeHandler);
    }
}
