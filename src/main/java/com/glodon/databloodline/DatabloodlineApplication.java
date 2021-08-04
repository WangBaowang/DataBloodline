package com.glodon.databloodline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.glodon.databloodline.dao")
public class DatabloodlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabloodlineApplication.class, args);
    }

}
