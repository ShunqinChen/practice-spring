package lol.kent.practice.spring.ribbon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 *
 * @auth kentc
 * @date 2018-09-03
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonSampleController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String consumer() {
        return restTemplate.getForObject("http://eureka-client/cient/hello", String.class);
    }
}
