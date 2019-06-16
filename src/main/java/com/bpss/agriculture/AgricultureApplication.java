package com.bpss.agriculture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Rocky Wu
 */
@SpringBootApplication
@MapperScan("com.bpss.agriculture.mapper")
public class AgricultureApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgricultureApplication.class, args);
    }

}
