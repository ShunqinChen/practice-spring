package lol.kent.practice.spring.socket.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lol.kent.practice.spring.socket.bean.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年06月16日 17:24
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@RestController
public class DemoController {

    private final SimpMessagingTemplate messagingTemplate;

    public DemoController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("hello/{appId}")
    @SendTo("/topic/messages")
    public JsonMessage hello(@Payload JsonMessage payload) {
        log.info("Input :{}", payload);
        payload.setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return payload;
    }

    @MessageMapping("print")
    public void testMsgTemplate(@Payload String name) {
        log.info("Name:{}", name);
        messagingTemplate.convertAndSend("/topic/messages", name);
    }

    @GetMapping("print")
    public void print(@RequestParam("text") String text) {
        JsonMessage message = new JsonMessage();
        message.setFrom("kent");
        message.setText(text);
        message.setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
