package lol.kent.practice.spring.nacos.web;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.util.List;
import lol.kent.practice.spring.nacos.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2019年10月16日 17:44
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/service")
public class DemoController {

    @NacosInjected
    NamingService namingService;

    @Autowired
    DemoService demoService;

    @GetMapping
    public List<Instance> get(@RequestParam("name") String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @PostMapping
    public void register() throws NacosException {
        namingService.registerInstance(demoService.getClass().getName(), "192.168.0.12", 8080);
        log.info("success");
    }


}
