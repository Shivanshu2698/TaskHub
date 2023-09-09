package com.springboot.test.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskHubApplication.class, args);
		
		System.out.println("Hello, This is Task Management Project");
	}

}
