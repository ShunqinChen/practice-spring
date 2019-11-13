package lol.kent.practice.core.framework.spring;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * <pre>
 *    类描述: 定时任务多线程池设置
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2019年11月12日 17:55
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    public static final String SCHEDULE_THREAD_NAME_FMT = "schedule-pool-%d";


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledThreadPoolExecutor taskExecutor() {
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern(SCHEDULE_THREAD_NAME_FMT).daemon(true).build();
        return new ScheduledThreadPoolExecutor(200, factory);
    }

}
