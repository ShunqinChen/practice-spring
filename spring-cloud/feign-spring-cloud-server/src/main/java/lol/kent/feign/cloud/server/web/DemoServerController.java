package lol.kent.feign.cloud.server.web;

import java.util.Random;
import lol.kent.feign.cloud.api.dto.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/books")
public class DemoServerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("{bookId}")
    public Book get(@PathVariable Integer bookId) {
        logger.info("book id:{}", bookId);
        return Book.builder().id(bookId).name("王五的书").build();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        logger.info("begin create book ");
        return Book.builder().id(new Random().nextInt()).name(book.getName()).build();
    }
}
