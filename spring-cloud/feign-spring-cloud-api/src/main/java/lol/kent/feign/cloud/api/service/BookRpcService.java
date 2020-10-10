package lol.kent.feign.cloud.api.service;

import lol.kent.feign.cloud.api.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年10月10日 15:50
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@FeignClient(value = "bookRpcService", url = "http://localhost:8080")
public interface BookRpcService {

    @GetMapping("/books/{bookId}")
    Book get(@PathVariable("bookId") Integer bookId);

    @PostMapping("/books")
    Book create(@RequestBody Book book);
}
