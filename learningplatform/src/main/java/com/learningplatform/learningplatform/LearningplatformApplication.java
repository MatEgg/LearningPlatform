package com.learningplatform.learningplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan("com.learningplatform.learningplatform.models")
@EnableJpaRepositories("com.learningplatform.learningplatform.models.dao")
public class LearningplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningplatformApplication.class, args);
	}
}
