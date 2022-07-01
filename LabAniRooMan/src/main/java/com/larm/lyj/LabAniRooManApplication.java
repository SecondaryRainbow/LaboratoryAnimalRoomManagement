package com.larm.lyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.larm.lyj.mapper")

public class LabAniRooManApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabAniRooManApplication.class, args);
    }

}
