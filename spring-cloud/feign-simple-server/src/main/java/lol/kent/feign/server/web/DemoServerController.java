package lol.kent.feign.server.web;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lol.kent.feign.server.api.DemoRpcService;
import lol.kent.feign.server.dto.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping
public class DemoServerController implements DemoRpcService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/demo")
    @Override
    public String print() {
        logger.info("print ok ");
        return "hello";
    }

    @GetMapping("/stores/{storeId}/books")
    @Override
    public List<Book> listByStore(@PathVariable String storeId) {
        logger.info("name:{}", storeId);

        return Arrays.asList(
                Book.builder().name("三体").id(1).build(),
                Book.builder().name("语文").id(2).build(),
                Book.builder().name("数学").id(3).build()
        );
    }

    @PostMapping("/books")
    @Override
    public Book create(Book book) {
        logger.info("begin create book ");
        return Book.builder().id(new Random().nextInt()).name(book.getName()).build();
    }
}
