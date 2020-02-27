package com.igeekhome.egobuygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class EgobuyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuyGatewayApplication.class, args);
    }

}
