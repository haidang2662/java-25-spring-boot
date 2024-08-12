package com.example.demoapp;


import com.example.demoapp.model.Book;
import com.example.demoapp.model.Classroom;
import com.example.demoapp.model.Student;
import com.example.demoapp.model.Teacher;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoappApplication.class , args);

		Student student = context.getBean(Student.class);
		System.out.println(student);
		student.getVehicle().run();

		Teacher teacher1 = context.getBean("teacher1" , Teacher.class);
		System.out.println("teacher1 : " +teacher1);
		Teacher teacher2 = context.getBean("teacher2" , Teacher.class);
		System.out.println("teacher2 : " +teacher2);

		Classroom classroom = context.getBean(Classroom.class);
		System.out.println("Classroom : " + classroom);
	}

}
