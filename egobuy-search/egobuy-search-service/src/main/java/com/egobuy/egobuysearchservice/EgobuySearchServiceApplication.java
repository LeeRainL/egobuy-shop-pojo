package com.egobuy.egobuysearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.igeekhome.egobuy.feign.clients")
public class EgobuySearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuySearchServiceApplication.class, args);
    }

}
