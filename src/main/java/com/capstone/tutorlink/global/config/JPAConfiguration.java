package com.capstone.tutorlink.global.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.ohgiraffers.tutorlinktest")
@EnableJpaRepositories(basePackages = "com.capstone.tutorlink")
public class JPAConfiguration {
}
