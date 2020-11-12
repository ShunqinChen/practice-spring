package lol.kent.feign.cloud.api.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年11月10日 17:42
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
public class CustomRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-CorpId", SecurityContextHolder.get("appName", String.class));
    }
}
