package com.app.todo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoBeApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
		}

	public static void main(String[] args) {
		SpringApplication.run(TodoBeApplication.class, args);
	}

}
