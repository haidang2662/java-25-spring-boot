package com.example.demoapp.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Bike implements Vehicle {
    @Override
    public void run() {
        System.out.println("Bike is running");
    }
}
