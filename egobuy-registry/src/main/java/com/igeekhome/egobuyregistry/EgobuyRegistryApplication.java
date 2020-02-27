package com.igeekhome.egobuyregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EgobuyRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuyRegistryApplication.class, args);
    }

}
