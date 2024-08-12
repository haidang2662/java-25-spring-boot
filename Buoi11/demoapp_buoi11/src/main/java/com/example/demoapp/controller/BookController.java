package com.example.demoapp.controller;


import com.example.demoapp.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Controller : Nơi tiếp nhận request từ client , xử lý và trả về response
// Có 2 dạng : 1 . Các controller trả về template(giao diện) : @Controller . Ngoài ra có thể trả về dạng dữ liệu json , xml ....
//             2 . Các controller trả về dữ liệu dạng json  , xml , .....  : @RestController
// @RestCOntroller = @Controller + @Responsebody
// ResponseEntity : Class đại diện cho 1 đối tượng response
@Controller
public class BookController {
    List<Book> books = new ArrayList<>(List.of(
            new Book(1, "Java Programing", "Author 1", 2021),
            new Book(2, "Python Programming", "Author 2", 2020),
            new Book(3, "C Programming", "Author 3", 2019)
    ));
    // Lấy danh sách tất cả cuốn sách
//    @GetMapping("/books") // GET : http.//localhost:8080/books
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<Book> getAllBooks(){
//
//        return books;
//    };

    //    Cách 2 :
    @GetMapping("/books") // GET : http.//localhost:8080/books
    public ResponseEntity<List<Book>> getAllBook() {
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    ;
    // Lây sách theo id
//    Cách 1 :
    // http://localhost:8001/books/1
    //http://localhost:8001/books/2
//    @GetMapping("/books/{id}")
//    @ResponseBody
//    public Book getBookById(@PathVariable int id) {
//        System.out.println("id =" +id);
//        for (Book book : books){
//            if(book.getId() == id) {
//                return book;
//            }
//        }
//        return null;
//    };
//}

    // Cách 2 :
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return new ResponseEntity<>(book, HttpStatus.OK); // 200
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }

    // 1. VIết API sắp xếp book theo năm xuất bản giảm dần
// http://localhost:8001/books/sortByYear
// 2 . Tìm kiếm search theo tên (trong tên chứa từ khóa bất kỳ)
    // http://localhost:8001/books//searchByTitle/keyword

// 3 : Tìm kiếm sách được xuất bản trong khoảng thời gian (fromYear , toYear)
// http://localhost:8001/books/searchByYear/{fromYear}/toYear/{toYear}

    // Câu 1 :
    @GetMapping("/books/sortByYear")

    public ResponseEntity<List<Book>> sortBooksByYearDesc() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingInt(Book::getYear).reversed());
        return new ResponseEntity<>(sortedBooks, HttpStatus.OK); // 200
    }

    // Câu 2 :
    @GetMapping("/books/searchByTitle/{keyword}")
    public ResponseEntity<List<Book>> searchBooksByTitle(@PathVariable String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }

        return ResponseEntity.ok(result);
    }

    // Câu 3 :

    @GetMapping("/books/searchByYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> searchBooksByYearRange(@PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= fromYear && book.getYear() <= toYear) {
                result.add(book);
            }
        }
        return ResponseEntity.ok(result);
    }
}





