package com.joe2shi.siamese.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.joe2shi.siamese.user", "com.joe2shi.siamese.common"})
public class SiameseUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseUserServiceApplication.class, args);
    }
}
