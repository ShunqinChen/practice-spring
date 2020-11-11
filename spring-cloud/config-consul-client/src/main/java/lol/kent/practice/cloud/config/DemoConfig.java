package lol.kent.practice.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年11月11日 19:12
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoConfig {

    @Autowired
    private DemoProperties demoProperties;

    public String key() {
        return demoProperties.getKey();
    }
}
