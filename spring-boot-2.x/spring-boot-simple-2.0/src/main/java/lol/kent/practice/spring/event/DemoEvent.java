package lol.kent.practice.spring.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年12月04日 17:01
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class DemoEvent extends ApplicationEvent {

    @Getter
    private DemoEventMessage message;

    public DemoEvent(Object source, DemoEventMessage message) {
        super(source);
        this.message = message;
    }
}
