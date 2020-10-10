package lol.kent.feign.server.api;

import feign.RequestLine;

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


    @RequestLine("GET /demo")
    String print();
}
