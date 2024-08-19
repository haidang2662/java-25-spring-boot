package com.example.demoapp.controller;

import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
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
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    //    // GET: http://localhost:8080/books
//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks() {
//        return new ResponseEntity<>(books, HttpStatus.CREATED);
//    }
//
    // GET: http://localhost:8080/books/1
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    //
//    // 1. Viết API sắp xếp book theo năm giảm dần
//    // http://localhost:8080/books/sortByYear
    @GetMapping("/sortByYear")
    public ResponseEntity<List<Book>> sortByYear() {
        List<Book> result = bookService.sortBookByYearDesc();
        return ResponseEntity.ok(result);
    }
//
    // 2. Tìm kiem sach theo ten (trong ten chua tu khoa bat ky)
    // http://localhost:8080/books/searchByTitle/{keyword}
    @GetMapping("/searchByTitle/{keyword}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String keyword) {
        List<Book> result = bookService.findBooksByTitle(keyword);
        return ResponseEntity.ok(result);
    }
//
//    // 3. Tim kiem sach duoc xuat ban trong khoang thoi gian (fromYear, toYear)
//    // http://localhost:8080/books/searchByYear/fromYear/{fromYear}/toYear/{toYear}
    @GetMapping("/searchByYear/fromYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> findBookInRangeYear(@PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> result = bookService.findBooksBeetweenYears(fromYear,toYear);
        return ResponseEntity.ok(result);
    }
}