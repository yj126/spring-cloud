package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author yujie
 * @Description
 * @Date Created in 2024-08-01 15:23
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.atguigu.cloud.mapper")
public class SeataStorageMainApp2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMainApp2002.class,args);
    }
}