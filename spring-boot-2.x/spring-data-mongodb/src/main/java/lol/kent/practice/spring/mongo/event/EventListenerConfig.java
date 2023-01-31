package lol.kent.practice.spring.mongo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * <pre>
 *    类描述:
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

    //    @EventListener(condition = "#event.message.type == 'demo'")
    @TransactionalEventListener(condition = "#event.message.type == 'demo'",
            phase = TransactionPhase.AFTER_COMMIT)
    public void handleTypeI(DemoEvent event) {
        log.info("Listener begin to work:{},{}", event.getTimestamp(), event.getMessage().getMessage());
    }
}
