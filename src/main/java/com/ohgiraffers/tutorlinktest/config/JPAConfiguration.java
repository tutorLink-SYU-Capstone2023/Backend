package com.ohgiraffers.tutorlinktest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.ohgiraffers.tutorlinktest")
@EnableJpaRepositories(basePackages = "com.ohgiraffers.tutorlinktest")
public class JPAConfiguration {
}
