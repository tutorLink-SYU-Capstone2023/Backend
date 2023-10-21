package com.capstone.tutorlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.capstone.tutorlink.domain")
public class TutorLinkTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorLinkTestApplication.class, args);
    }

}
