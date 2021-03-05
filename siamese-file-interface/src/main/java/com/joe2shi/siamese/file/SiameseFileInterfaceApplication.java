package com.joe2shi.siamese.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.joe2shi.siamese.file", "com.joe2shi.siamese.common"})
public class SiameseFileInterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseFileInterfaceApplication.class, args);
    }
}
