package lol.kent.feign.cloud.client.web;

import java.util.Random;
import lol.kent.feign.cloud.api.dto.Book;
import lol.kent.feign.cloud.api.service.BookRpcService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BookClientController(BookRpcService bookRpcService) {
        this.bookRpcService = bookRpcService;
    }

    @GetMapping
    public Book get() {
        return bookRpcService.get(new Random().nextInt());
    }

    @PostMapping
    public Book create() {
        Book book = Book.builder().name("李四的书").build();
        return bookRpcService.create(book);
    }
}
