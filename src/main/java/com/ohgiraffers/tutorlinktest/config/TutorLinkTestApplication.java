package com.ohgiraffers.tutorlinktest.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ohgiraffers.tutorlinktest")
public class TutorLinkTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorLinkTestApplication.class, args);
    }

}
