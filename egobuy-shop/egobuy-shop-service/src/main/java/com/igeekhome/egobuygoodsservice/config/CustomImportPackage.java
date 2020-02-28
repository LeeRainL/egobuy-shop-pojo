package com.igeekhome.egobuygoodsservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置额外扫描的包
 * @ComponentScan 不能放在主程序类，否则会覆盖SpringBoot默认扫描的包
 * @author yadonghe
 * @date 2020-02-28 16:02
 */
@Configuration
@ComponentScan(basePackages = "com.igeekhome.egobuy")
public class CustomImportPackage {
}
