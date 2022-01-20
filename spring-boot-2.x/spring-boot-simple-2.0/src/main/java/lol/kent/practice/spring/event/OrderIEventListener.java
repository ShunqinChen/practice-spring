package lol.kent.practice.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *    类描述: 第1个执行的事件监听器
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2022年01月20日 18:48
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@Component
public class OrderIEventListener implements SmartApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("smart listener 1");
    }

    /**
     * 这是我们的监听器智能所在之一，能够根据事件类型动态监听
     *
     * @param eventType 事件class类型
     * @return 是否支持该事件类型
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == DemoEvent.class;
    }

    /**
     * 这是我们的监听器智能所在之二，能够根据事件发布者类型动态监听
     *
     * @param sourceType 事件发布者(件源), 通常是applicationEventPublisher调用类,如此处的MainController
     * @return 是否支持该类型事件源
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == MainController.class;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
