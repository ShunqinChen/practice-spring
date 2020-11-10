package lol.kent.feign.cloud.client;

import lol.kent.feign.cloud.api.service.BookRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = BookRpcService.class)
@SpringBootApplication(scanBasePackages = "lol.kent.feign.*")
public class Bootstrap {


    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}