package com.example.managelibraryrestful.service;

import com.example.managelibraryrestful.entity.Book;
import com.example.managelibraryrestful.entity.Borrow;
import com.example.managelibraryrestful.entity.Reader;
import com.example.managelibraryrestful.exceptionhandling.exception.InvalidBookBorrowQuantityException;
import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import com.example.managelibraryrestful.model.request.BorrowRequest;
import com.example.managelibraryrestful.model.response.BorrowResponse;
import com.example.managelibraryrestful.repository.BookRepository;
import com.example.managelibraryrestful.repository.BorrowRepository;
import com.example.managelibraryrestful.repository.ReaderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowService {

    BorrowRepository borrowRepository;
    ObjectMapper objectMapper;
    BookRepository bookRepository;
    ReaderRepository readerRepository;

    public List<BorrowResponse> getBorrows() {
        List<Borrow> borrowList = borrowRepository.findAll();
        return borrowList
                .stream()
                .map(s -> objectMapper.convertValue(s, BorrowResponse.class))
                .toList();
    }

    public BorrowResponse creatBorrow(BorrowRequest request) throws ObjectNotFoundException, InvalidBookBorrowQuantityException {
        // tìm sách theo id xem có không
//        Optional<Book> bookOptional = bookRepository.findById(request.getBookId());
//        if(bookOptional.isEmpty()) {
//            throw new ObjectNotFoundException("Không tìm thấy sách mang mã " + request.getBookId());
//        }
//        Book book = bookOptional.get();

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(
                        () -> new ObjectNotFoundException("Không tìm thấy sách mang mã " + request.getBookId())
                );

        // tìm bạn đọc theo id xem có không
        Reader reader = readerRepository.findById(request.getReaderId())
                .orElseThrow(
                        () -> new ObjectNotFoundException("Không tìm thấy bạn đọc mang mã " + request.getReaderId())
                );

        // nếu có thì cho mượn (không quá 5 quyền 1 lần)
        if (request.getQuantity() > 5) {
            throw new InvalidBookBorrowQuantityException("Số sách mượn vượt quá 5");
        }

        Borrow borrow = Borrow.builder()
                .book(book)
                .reader(reader)
                .quantity(request.getQuantity())
                .borrowDate(LocalDate.now())
                .expectedReturnDate(request.getExpectedReturnDate())
                .build();
        borrowRepository.save(borrow);

        return objectMapper.convertValue(borrow, BorrowResponse.class);
    }

    public BorrowResponse getBorrowDetail(Long idBorrow) throws ObjectNotFoundException {
        Optional<Borrow> borrowOptional = borrowRepository.findById(idBorrow);
        if (borrowOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay luot muon sach co id : " + idBorrow);
        }
        return objectMapper.convertValue(borrowOptional, BorrowResponse.class);
    }

    public void deleteBorrow(Long idBorrow) {
        borrowRepository.deleteById(idBorrow);
    }


    public BorrowResponse updateBorrow(BorrowRequest request, Long idBorrow) throws ObjectNotFoundException {
        Optional<Borrow> borrowOptional = borrowRepository.findById(idBorrow);
        if (borrowOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tm thay luot muon sach co id : " + idBorrow);
        }
        Borrow borrow = borrowOptional.get();

        Optional<Book> optionalBook = bookRepository.findById(request.getBookId());
        Book book = objectMapper.convertValue(optionalBook, Book.class);
        Optional<Reader> optionalReader = readerRepository.findById(request.getReaderId());
        Reader reader = objectMapper.convertValue(optionalReader, Reader.class);

        borrow.setBook(book);
        borrow.setReader(reader);
        borrow.setQuantity(request.getQuantity());
        borrow.setExpectedReturnDate(request.getExpectedReturnDate());
        borrowRepository.save(borrow);
        BorrowResponse borrowResponse = objectMapper.convertValue(borrow, BorrowResponse.class);
        return borrowResponse;
    }

}
