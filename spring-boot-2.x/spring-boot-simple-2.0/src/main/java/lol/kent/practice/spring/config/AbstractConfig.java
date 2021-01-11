package lol.kent.practice.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年01月11日 20:10
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Configuration
public abstract class AbstractConfig {

    /***只使用此方法成功*/
//    @Bean
//    protected abstract AppInfo init();

    /***结合使用以下两方法再次失败*/
    protected abstract AppInfo init();

    @Bean
    public AppInfo appInfo() {
        return this.init();
    }

}
