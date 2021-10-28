package com.example.wematch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WeMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeMatchApplication.class, args);
    }

}
