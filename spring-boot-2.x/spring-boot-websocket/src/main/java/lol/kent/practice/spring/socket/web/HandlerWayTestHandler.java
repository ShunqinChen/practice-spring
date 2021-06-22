package lol.kent.practice.spring.socket.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * <pre>
 *     方法二
 *    类描述: 使用Handler方式进行测试
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年06月19日 17:31
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
public class HandlerWayTestHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Text Payload:{}", payload);
        session.sendMessage(new TextMessage("Handler receive msg:" + payload));
    }
}
