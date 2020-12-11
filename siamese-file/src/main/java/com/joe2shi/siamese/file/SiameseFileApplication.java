package com.joe2shi.siamese.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SiameseFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiameseFileApplication.class, args);
    }

}
