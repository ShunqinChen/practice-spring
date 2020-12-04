package lol.kent.practice.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月04日 17:15
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("event")
public class DemoEventController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public void print() {
        DemoEventMessage msg = DemoEventMessage.builder()
                .type("demo")
                .message("他来了")
                .build();
        DemoEvent event = new DemoEvent(this, msg);
        publisher.publishEvent(event);
    }
}
