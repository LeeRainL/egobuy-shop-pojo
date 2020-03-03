package com.igeekhome.egobuycontentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement //开启事务管理
@MapperScan(basePackages = "com.igeekhome.egobuycontentservice.mapper")
public class EgobuyContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuyContentServiceApplication.class, args);
    }

}
