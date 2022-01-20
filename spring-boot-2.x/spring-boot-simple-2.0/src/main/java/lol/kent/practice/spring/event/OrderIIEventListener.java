package lol.kent.practice.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *    类描述: 第2个执行的事件监听器
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2022年01月20日 18:49
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@Component
public class OrderIIEventListener implements SmartApplicationListener {


    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("smart listener 2");

    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == DemoEvent.class;
    }
}
