package com.igeekhome.egobuygoodsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement //开启事务管理
@MapperScan(basePackages = "com.igeekhome.egobuygoodsservice.mapper")
public class EgobuyGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuyGoodsServiceApplication.class, args);
    }

}
