package com.joe2shi.siamese.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SiameseUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiameseUploadApplication.class, args);
    }

}
