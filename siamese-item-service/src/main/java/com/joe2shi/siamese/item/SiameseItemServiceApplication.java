package com.joe2shi.siamese.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.joe2shi.siamese.item", "com.joe2shi.siamese.common"})
public class SiameseItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseItemServiceApplication.class, args);
    }
}
