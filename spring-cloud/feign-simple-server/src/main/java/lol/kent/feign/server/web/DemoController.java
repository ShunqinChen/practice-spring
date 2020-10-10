package lol.kent.feign.server.web;

import lol.kent.feign.server.api.DemoRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年10月10日 14:01
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController implements DemoRpcService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    @Override
    public String print() {
        logger.info("print ok ");
        return "hello";
    }
}
