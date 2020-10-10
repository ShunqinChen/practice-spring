package lol.kent.feign.server.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;
import lol.kent.feign.server.dto.Book;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年10月10日 14:14
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public interface DemoRpcService {

    /**
     * 基础GET测试
     *
     * @return String
     */
    @RequestLine("GET /demo")
    String print();

    /**
     * list 接口及路径参数测试
     *
     * @param storeId 书库ID
     * @return list
     */
    @RequestLine("GET /stores/{storeId}/books")
    List<Book> listByStore(@Param("storeId") String storeId);

    /**
     * POST测试 测试对象接收
     *
     * @param book client端传入对象,传入时ID假定id为空
     * @return Book 创建结果,对ID进行随机赋值
     */
    @RequestLine("POST /books")
    Book create(@QueryMap Book book);
}
