package com.xia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xia.dao")
public class BootFristApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootFristApplication.class, args);
    }

}
