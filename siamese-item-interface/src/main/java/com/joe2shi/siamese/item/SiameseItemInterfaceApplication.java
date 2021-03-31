package com.joe2shi.siamese.item;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableDistributedTransaction
@ComponentScan({"com.joe2shi.siamese.item", "com.joe2shi.siamese.common"})
public class SiameseItemInterfaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseItemInterfaceApplication.class, args);
    }
}
