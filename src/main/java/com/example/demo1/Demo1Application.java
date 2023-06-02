package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication()
@ComponentScan(basePackages= "com.example.demo1")
@EnableJpaRepositories( basePackages = "com.example.demo1")
@ComponentScan("com.example.demo1")
@EntityScan("com.example.demo1")
public class Demo1Application{
	
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		   
		 System.out.println("test");
	}
	
	@Bean
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}	

}
