package lol.kent.practice.cloud.admin;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import java.time.Duration;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年11月07日 15:52
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Configuration(proxyBeanMethods = false)
public class NotifyReminderConfig {

    private final InstanceRepository repository;

    private final ObjectProvider<List<Notifier>> otherNotifiers;

    public NotifyReminderConfig(InstanceRepository repository, ObjectProvider<List<Notifier>> otherNotifiers) {
        this.repository = repository;
        this.otherNotifiers = otherNotifiers;
    }

//    @Bean
//    public LoggingNotifier notifier() {
//        return new LoggingNotifier(repository);
//    }

    @Bean
    public CustomNotifier notifier() {
        return new CustomNotifier(repository);
    }

    @Primary
    @Bean(initMethod = "start", destroyMethod = "stop")
    public RemindingNotifier remindingNotifier(CustomNotifier notifier) {
        RemindingNotifier remindingNotifier = new RemindingNotifier(notifier, repository);
        //间隔多长时间执行通知
        remindingNotifier.setReminderPeriod(Duration.ofSeconds(5));
        //间隔多长时间检查服务,检查但不通知,通知按上面的时间
        remindingNotifier.setCheckReminderInverval(Duration.ofSeconds(5));
        return remindingNotifier;
    }
}
