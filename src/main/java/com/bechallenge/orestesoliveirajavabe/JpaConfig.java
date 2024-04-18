package com.bechallenge.orestesoliveirajavabe;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.bechallenge.orestesoliveirajavabe.repo")
public class JpaConfig {
}

