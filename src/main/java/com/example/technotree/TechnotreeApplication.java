package com.example.technotree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class TechnotreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnotreeApplication.class, args);
    }

}
