package com.conectdoe.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConectDoeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConectDoeApiApplication.class, args);
    }

}
