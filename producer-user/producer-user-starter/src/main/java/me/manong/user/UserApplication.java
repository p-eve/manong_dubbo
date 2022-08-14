package me.manong.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("me.manong.user.mapper")
public class UserApplication {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(UserApplication.class, args);
        System.out.println("dubbo service started");

    }
}