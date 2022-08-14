package me.manong.question;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("me.manong.question.mapper")
public class QuestionsApplication {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(QuestionsApplication.class, args);
        System.out.println("dubbo service started");

    }
}