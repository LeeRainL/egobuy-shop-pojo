package com.igeekhome.egobuygoodsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.igeekhome.egobuygoodsservice.mapper")
public class EgobuyGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuyGoodsServiceApplication.class, args);
    }

}
