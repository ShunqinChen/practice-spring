package lol.kent.practice.cloud.admin;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import de.codecentric.boot.admin.server.notify.LoggingNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年11月07日 16:05
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class CustomNotifier extends AbstractEventNotifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingNotifier.class);

    public CustomNotifier(InstanceRepository repository) {
        super(repository);
    }


    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent) {
                LOGGER.info("发生事件的实例: {} ({}) 事件: {}", instance.getRegistration().getName(), event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
            } else {
                LOGGER.info("2 发生事件的实例: {} ({}) 事件Type: {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }

}
