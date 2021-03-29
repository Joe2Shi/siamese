package com.joe2shi.siamese.txmanage;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@ComponentScan({"com.joe2shi.siamese.txmanage", "com.joe2shi.siamese.common"})
public class SiameseItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseItemServiceApplication.class, args);
    }
}
