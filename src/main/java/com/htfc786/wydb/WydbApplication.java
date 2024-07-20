package com.htfc786.wydb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.htfc786.wydb.mapper")
public class WydbApplication {
    public static void main( String[] args ){
        SpringApplication.run(WydbApplication.class, args);
    }
}
