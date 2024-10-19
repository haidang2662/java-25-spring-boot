package com.example.adminweb.service;

import com.example.adminweb.entity.Book;
import com.example.adminweb.entity.BookBorrow;
import com.example.adminweb.entity.User;
import com.example.adminweb.exceptionHandling.ObjectNotFoundException;
import com.example.adminweb.model.request.BookBorrowCreationRequest;
import com.example.adminweb.model.request.UpdateBookBorrowRequest;
import com.example.adminweb.model.response.BookBorrowResponse;
import com.example.adminweb.repository.BookBorrowRepository;
import com.example.adminweb.repository.BookRepository;
import com.example.adminweb.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookBorrowService {

    ObjectMapper objectMapper;

    UserRepository userRepository;

    BookRepository bookRepository;

    BookBorrowRepository bookBorrowRepository;

    public void create(BookBorrowCreationRequest bookBorrowRequest) {
        Optional<User> userOptional = userRepository.findById(bookBorrowRequest.getReaderId());
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay ban doc");
        }

        Optional<Book> bookOptional = bookRepository.findById(bookBorrowRequest.getBookId());
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay thong tin sach");
        }

        BookBorrow bookBorrow = BookBorrow.builder()
                .borrower(userOptional.get())
                .book(bookOptional.get())
                .quantity(bookBorrowRequest.getQuantity())
                .createdDate(LocalDate.now())
                .expectedReturnDate(bookBorrowRequest.getExpectedReturnDate())
                .build();

        bookBorrowRepository.save(bookBorrow);
    }

    public Page<BookBorrowResponse> findAllBookBorrows(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page -1 , pageSize , Sort.by("id").descending());
        Page<BookBorrow> bookBorrowPage = bookBorrowRepository.findAll(pageable);
        List<BookBorrow> bookBorrows = bookBorrowPage.getContent();
        List<BookBorrowResponse> bookBorrowResponses = bookBorrows
                .stream()
                .map(bookBorrowTemp -> objectMapper.convertValue(bookBorrowTemp , BookBorrowResponse.class))
                .toList();
        return new PageImpl<>(bookBorrowResponses , pageable , bookBorrowPage.getTotalElements());
    }

    public void deleteByIdBookBorrow(Long id) {
        bookBorrowRepository.deleteById(id);
    }


    public UpdateBookBorrowRequest findBookBorrowById(Long id) {
        Optional<BookBorrow> bookBorrowOptional = bookBorrowRepository.findById(id);
        if(bookBorrowOptional.isEmpty()){
            throw new ObjectNotFoundException("Khong tim thay luot muon sach mang id : " +id);
        }

        BookBorrow bookBorrow = bookBorrowOptional.get();
        UpdateBookBorrowRequest request = objectMapper.convertValue(bookBorrow, UpdateBookBorrowRequest.class);
        request.setBookId(bookBorrow.getBook().getId());
        request.setReaderId(bookBorrow.getBorrower().getId());
        return request;
    }

    public void updateBookBorrow(UpdateBookBorrowRequest updateBookBorrow) {

        Optional<BookBorrow> bookBorrowOptional = bookBorrowRepository.findById(updateBookBorrow.getId());
        if (bookBorrowOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy lượt mượn sách mang Id: " + updateBookBorrow.getId());
        }

        Optional<User> userOptional = userRepository.findById(updateBookBorrow.getReaderId());
        Optional<Book> bookOptional = bookRepository.findById(updateBookBorrow.getBookId());

        BookBorrow bookBorrow = bookBorrowOptional.get();
        bookBorrow.setBorrower(objectMapper.convertValue(userOptional , User.class));
        bookBorrow.setBook(objectMapper.convertValue(bookOptional , Book.class));
//        bookBorrow.setCreatedDate(updateBookBorrow.);
        bookBorrow.setQuantity(updateBookBorrow.getQuantity());
        bookBorrow.setExpectedReturnDate(updateBookBorrow.getExpectedReturnDate());

        bookBorrowRepository.save(bookBorrow);
    }
}
