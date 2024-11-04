package com.example.demo_send_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoSendEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSendEmailApplication.class, args);
    }

}
