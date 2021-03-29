package com.joe2shi.siamese.txmanage;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagerServer
@ComponentScan({"com.joe2shi.siamese.txmanage", "com.joe2shi.siamese.common"})
public class SiameseTXManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiameseTXManageApplication.class, args);
    }
}
