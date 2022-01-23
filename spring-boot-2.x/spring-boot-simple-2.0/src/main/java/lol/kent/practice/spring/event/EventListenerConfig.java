package lol.kent.practice.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *    类描述: 监听实现方式二
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月04日 17:16
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@Component
public class EventListenerConfig {

    @EventListener(condition = "#event.message.type == 'demo'")
    public void handleTypeI(DemoEvent event) {
        log.info("Listener begin to work:{},{}", event.getTimestamp(), event.getMessage().getMessage());
    }


    @EventListener(condition = "#event.type == 'course'")
    public void handleCourse(DemoEvent event) {
        log.info("Course Listener msg work", event.getTimestamp(), event.getMessage().getMessage());
    }
}
