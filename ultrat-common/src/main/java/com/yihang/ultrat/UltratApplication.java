package com.yihang.ultrat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.yihang.ultrat.**.mapper"})
public class UltratApplication {
    public static void main(String[] args) {
        SpringApplication.run(UltratApplication.class, args);
    }
}
