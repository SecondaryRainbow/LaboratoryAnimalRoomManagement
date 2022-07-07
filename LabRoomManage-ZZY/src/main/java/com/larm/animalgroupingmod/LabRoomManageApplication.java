package com.larm.animalgroupingmod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.larm.animalgroupingmod.mapper")
public class LabRoomManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabRoomManageApplication.class, args);
    }

}
