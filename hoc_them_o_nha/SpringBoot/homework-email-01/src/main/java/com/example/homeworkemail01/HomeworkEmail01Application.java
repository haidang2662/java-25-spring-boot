package com.example.homeworkemail01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HomeworkEmail01Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkEmail01Application.class, args);
    }

}
