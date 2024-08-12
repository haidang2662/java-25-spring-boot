package com.example.demoapp.model;

import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {
    @Override
    public void run() {
        System.out.println("Car is running");
    }
}
