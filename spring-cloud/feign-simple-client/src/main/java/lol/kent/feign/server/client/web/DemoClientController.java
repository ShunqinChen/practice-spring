package lol.kent.feign.server.client.web;

import feign.Feign;
import feign.Logger.Level;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import java.util.List;
import lol.kent.feign.server.api.DemoRpcService;
import lol.kent.feign.server.dto.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private DemoRpcService demoRpcService;

    public DemoClientController() {
        this.demoRpcService = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(this.getClass()))
                .logLevel(Level.FULL)
                .target(DemoRpcService.class, "http://localhost:8080");
    }

    @GetMapping
    public String call() {
        return demoRpcService.print();
    }

    @GetMapping("/books")
    public List<Book> callListBook() {
        return demoRpcService.listByStore("20");
    }

    @PostMapping
    public Book create() {
        Book book = Book.builder()
                .name("张三的书")
                .build();
        return demoRpcService.create(book);
    }
}
