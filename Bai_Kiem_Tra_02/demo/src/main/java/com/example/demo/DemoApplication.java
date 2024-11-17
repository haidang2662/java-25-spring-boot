package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class DemoApplication {

	// DAO - data access object --> repository
	// impl -> implement
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}