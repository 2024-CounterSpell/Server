package com.counterspell.yunjisang.counterspell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CounterspellApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterspellApplication.class, args);
    }

}
