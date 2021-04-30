package lol.kent.practice.sleuth.server.api;

import lol.kent.feign.cloud.api.dto.Book;
import lol.kent.feign.cloud.api.service.BookRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年04月23日 11:49
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("api")
public class ApiController {

    private final BookRpcService bookRpcService;

    public ApiController(BookRpcService bookRpcService) {
        this.bookRpcService = bookRpcService;
    }

    @GetMapping("hello")
    public String get() {
        Book record = new Book();
        record.setId(1);
        record.setName("Demo Book");
        Book book = bookRpcService.create(record);
//        Book book = bookRpcService.get(1);
        return book.getName();
    }
}
