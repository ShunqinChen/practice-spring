package lol.kent.practice.spring.web;

import lol.kent.practice.spring.config.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2021年01月11日 20:14
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private AppInfo appInfo;

    @GetMapping
    public String print() {
        return appInfo.getKey();
    }
}
