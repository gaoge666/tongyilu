package com.example.tongyilu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.tongyilu.dao")
public class TongyiluApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongyiluApplication.class, args);
    }
}
