package com.joe2shi.siamese.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.joe2shi.siamese.user", "com.joe2shi.siamese.common"})
public class SiameseUserInterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseUserInterfaceApplication.class, args);
    }
}
