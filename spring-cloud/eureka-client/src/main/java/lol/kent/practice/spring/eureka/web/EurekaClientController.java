package lol.kent.practice.spring.eureka.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @auth kentc
 * @date 2018-09-03
 */
@RestController
@RequestMapping("/client")
public class EurekaClientController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @GetMapping("hello")
    public String hello() {
        ServiceInstance instance = client.getLocalServiceInstance();

        logger.info("service host:{} service-id:{}", instance.getHost(), instance.getServiceId());

        return "hello service:".concat(instance.getServiceId());
    }
}
