package com.example.demo.controller;

import com.example.demo.model.Book;

import com.example.demo.model.PageResponse;
import com.example.demo.model.PageResponseIplm;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/*
 * Controller: Nơi tiếp nhận request từ client, xử lý và trả về response
 * - @Controller: Các controller trả về Template (giao diện). Ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
 * - @RestController: Các controller trả về dữ liệu dạng JSON, XML, ...
 * - @Rescontroller = @Controller + @ResponseBody
 * - @ResponseBody: Chỉ trả về dữ liệu, không trả về Template. Dữ liệu trả về có thể là JSON, XML, ...
 * - ResponseEntity<?>: Class dại diện cho 1 dối tượng response , có thể set status code, header, ...
 * */
//@RestController
@Controller
public class BookController {
    List<Book> books = new ArrayList<>(List.of());

    public BookController() {
        Faker faker = new Faker();
        for (int i = 0 ; i < 30 ; i++) {
            Book book = Book.builder()
                    .id(i + 1)
                    .title(faker.book().title())
                    .author(faker.book().author())
                    .year(faker.number().numberBetween(1950,2021))
                    .build();
            books.add(book);
        }
    }




    @GetMapping
    public  String getHomePage(Model model , @RequestParam(required = false) String title) {
        Book book = books.get(0);

        List<Book> newBooks = new ArrayList<>();
        if(title != null){
            newBooks = books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .toList();
        } else {
            newBooks = books;
        }

        model.addAttribute("book" , book);
        model.addAttribute("books" , newBooks);
        return "index";
    }

    // Phân trang

    // http://localhost:8080/books (thường mặc định ở page 1)
    // http://localhost:8080/books?page=1&size=10
    // current page : Trang hien tai
    // PageSize : So luong phan tu tren 1 trang
    // totalElement : Tong so phan tu
    // totalPage : Tong so trang
    // content : Dư lieu tren trang hien tai

    @GetMapping("/books")

    public String getBooksPage(Model model ,
                               @RequestParam(required = false , defaultValue = "1") int page ,
                               @RequestParam(required = false , defaultValue = "10") int size)
    {
        PageResponse<Book> pageResponse = PageResponseIplm.<Book>builder()
                .currentPage(page)
                .pageSize(size)
                .data(books)
                .build();
        
        model.addAttribute("books" , books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String getBookDetail(@PathVariable int id , Model model){
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("book",book);
        return "book-detail";
    }

    // local host 8080
    @GetMapping("/blog")
    public  String getBlogPage() {
        return "admin/blog";
    }
}