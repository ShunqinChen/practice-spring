package lol.kent.practice.spring.config;

import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年01月11日 20:13
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Configuration
public class WebConfig extends AbstractConfig {

    @Override
    protected AppInfo init() {
        return new AppInfo("123", "465");
    }
}
