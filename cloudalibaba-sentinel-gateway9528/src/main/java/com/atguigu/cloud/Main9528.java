package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author yujie
 * @Description
 * @Date Created in 2024-07-31 18:02
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9528
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main9528.class,args);
    }
}

