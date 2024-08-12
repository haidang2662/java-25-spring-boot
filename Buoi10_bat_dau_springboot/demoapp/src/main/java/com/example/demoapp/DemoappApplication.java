package com.example.demoapp;


import com.example.demoapp.model.Book;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);

		Faker faker = new Faker();
		System.out.println(faker.name().fullName());

		Book book = new Book();
		book.setId(1);
		book.setTitle("The lord of the ring");
		book.setAuthor("J.R.R Tolken");
		book.setYear(1954);

		System.out.println(book);

		// Khoi tao doi tuong book su dung builder

		Book book2 = Book.builder()
				.year(1937)
				.id(2)
				.title("The Hobbit")
				.build();
		System.out.println(book2);


	}

}
