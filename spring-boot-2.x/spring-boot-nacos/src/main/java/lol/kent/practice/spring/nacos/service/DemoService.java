package lol.kent.practice.spring.nacos.service;

import org.springframework.stereotype.Service;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2019年10月16日 17:43
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Service
public class DemoService {


    public String print(String name) {
        System.out.println("Hello");
        return "hello";
    }
}
