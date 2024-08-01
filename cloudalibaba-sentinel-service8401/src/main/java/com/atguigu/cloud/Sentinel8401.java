package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author yujie
 * @Description
 * @Date Created in 2024-07-29 11:02
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Sentinel8401
{
    public static void main(String[] args)
    {
        SpringApplication.run(Sentinel8401.class,args);
    }
}