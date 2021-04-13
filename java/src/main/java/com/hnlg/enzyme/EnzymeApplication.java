package com.hnlg.enzyme;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//扫描dao层的包
@MapperScan("com.hnlg.enzyme.dao")
//扫描实体类包
@EntityScan("com.hnlg.enzyme.entity.po")
//允许异步
@EnableAsync
public class EnzymeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnzymeApplication.class, args);
    }
    
}
