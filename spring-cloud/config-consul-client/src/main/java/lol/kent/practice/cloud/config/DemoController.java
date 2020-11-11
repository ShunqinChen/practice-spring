package lol.kent.practice.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年11月11日 18:30
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RefreshScope
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoConfig demoConfig;


    /***需要refresh scope才能刷新*/
    @Value("${demo.name}")
    private String name;

    @GetMapping
    public String get() {
        logger.info(name);
        return name;
    }

    @GetMapping("/key")
    public String getKey() {
        logger.info(demoConfig.key());
        return demoConfig.key();
    }
}
