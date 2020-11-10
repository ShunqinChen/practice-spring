package lol.kent.feign.cloud.client.web;

import java.util.Random;
import lol.kent.feign.cloud.api.dto.Book;
import lol.kent.feign.cloud.api.service.BookRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年10月10日 16:01
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("/books")
public class BookClientController {

    private BookRpcService bookRpcService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Registration registration;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public BookClientController(BookRpcService bookRpcService) {
        this.bookRpcService = bookRpcService;
    }

    @GetMapping
    public Book get() {
        ServiceInstance si = this.registration;
        return bookRpcService.get(new Random().nextInt());
    }

    @PostMapping
    public Book create() {
        Book book = Book.builder().name("李四的书").build();
        return bookRpcService.create(book);
    }
}
