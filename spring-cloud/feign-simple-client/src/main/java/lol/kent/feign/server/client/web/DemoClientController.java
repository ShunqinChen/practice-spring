package lol.kent.feign.server.client.web;

import feign.Feign;
import feign.Logger.Level;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lol.kent.feign.server.api.DemoRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年10月10日 14:30
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("/client")
public class DemoClientController {

    @GetMapping
    public String call() {
        DemoRpcService service = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(this.getClass()))
                .logLevel(Level.FULL)
                .target(DemoRpcService.class, "http://localhost:8080");
        return service.print();
    }
}
