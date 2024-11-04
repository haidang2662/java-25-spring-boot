package com.example.manage_student_swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ManageStudentSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageStudentSwaggerApplication.class, args);
    }

}
