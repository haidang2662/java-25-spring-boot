package com.example.demoapp.controller;


import com.example.demoapp.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Controller : Nơi tiếp nhận request từ client , xử lý và trả về response
// Có 2 dạng : 1 . Các controller trả về template(giao diện) : @Controller . Ngoài ra có thể trả về dạng dữ liệu json , xml ....
//             2 . Các controller trả về dữ liệu dạng json  , xml , .....  : @RestController
//
@RestController
public class BookController {

    List<Book> books = new ArrayList<>(List.of(
            new Book(1 , "Java Programing","Author 1" , 2021),
            new Book(2, "Python Programming" , "Author 2" , 2020),
            new Book(3, "C Programming" , "Author 3" , 2019)
    ));
    // Lấy danh sách tất cả cuốn sách
    @GetMapping("/books") // GET : http.//localhost:8080/books
    public List<Book> getAllBooks(){
        return books;
    };

    // Lây sách theo id
    // http://localhost:8001/books/1
    //http://localhost:8001/books/2
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        System.out.println("id =" +id);
        for (Book book : books){
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    };
}
