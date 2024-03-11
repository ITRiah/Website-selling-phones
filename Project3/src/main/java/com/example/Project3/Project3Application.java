package com.example.Project3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing // Kết hợp EntityListener
@EnableScheduling // lên lịch job
@EnableCaching

public class Project3Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);
	}
}
