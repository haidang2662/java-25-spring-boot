package com.example.adminweb.service;

import com.example.adminweb.constant.TelephoneStatus;
import com.example.adminweb.entity.Telephone;
import com.example.adminweb.exceptionHandling.ObjectNotFoundException;
import com.example.adminweb.model.request.TelephoneCreationRequest;
import com.example.adminweb.model.request.UpdateTelephoneRequest;
import com.example.adminweb.model.response.TelephoneResponse;
import com.example.adminweb.repository.TelephoneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelephoneService {

    TelephoneRepository telephoneRepository;

    ObjectMapper objectMapper;

    public void createTelephone(TelephoneCreationRequest telephoneCreationRequest) {
        Telephone telephone = objectMapper.convertValue(telephoneCreationRequest, Telephone.class);
        telephoneRepository.save(telephone);
    }

    public Page<TelephoneResponse> findAllTelephones(int page, int pageSize) {
        // 1 - tìm trong DB
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<Telephone> telephonePage = telephoneRepository.findAll(pageable);

        // 2 - convert từ entity sang response
        List<Telephone> telephones = telephonePage.getContent();

        // 2.1 - code chay thông thường
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (int i = 0; i < books.size(); i++) {
//            Book bookTemp = books.get(i);
//
//            BookResponse response = new BookResponse();
//            response.setId(bookTemp.getId());
//            response.setName(bookTemp.getName());
//            response.setAuthor(bookTemp.getAuthor());
//            response.setPublishedAt(bookTemp.getPublishedAt());
//            response.setPublisher(bookTemp.getPublisher());
//            response.setPrice(bookTemp.getPrice());
//            response.setRating(bookTemp.getRating());
//            response.setTotalPages(bookTemp.getTotalPages());
//            response.setStatus(bookTemp.getStatus());
//
//            bookResponses.add(response);
//        }

        // 2.2 - dùng builder
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (int i = 0; i < books.size(); i++) {
//            Book bookTemp = books.get(i);
//
//            BookResponse response = BookResponse.builder()
//                    .id(bookTemp.getId())
//                    .name(bookTemp.getName())
//                    .author(bookTemp.getAuthor())
//                    .publishedAt(bookTemp.getPublishedAt())
//                    .publisher(bookTemp.getPublisher())
//                    .price(bookTemp.getPrice())
//                    .rating(bookTemp.getRating())
//                    .totalPages(bookTemp.getTotalPages())
//                    .status(bookTemp.getStatus())
//                    .build();
//
//            bookResponses.add(response);
//        }

        // 2.3- dùng object mapper
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (int i = 0; i < books.size(); i++) {
//            Book bookTemp = books.get(i);
//
//            BookResponse response = objectMapper.convertValue(bookTemp, BookResponse.class);
//
//            bookResponses.add(response);
//        }

        // 2.4 - dùng object mapper kết hợp với stream của java 8
        List<TelephoneResponse> telephoneResponses = telephones
                .stream()
                .map(telephoneTemp -> objectMapper.convertValue(telephoneTemp, TelephoneResponse.class))
                .toList();

        // 3 - trả về kết quả
        return new PageImpl<>(telephoneResponses, pageable, telephonePage.getTotalElements());
    }

    public void deleteByIdTelephone(long id) {
        telephoneRepository.deleteById(id);
    }

    //Todo : Doc ky de hieu sau them doan code nay lien quan den ca ObjectNotFoundException , java 8 ,
    public UpdateTelephoneRequest findTelephoneById(long id) {
        Optional<Telephone> telephoneOptional = telephoneRepository.findById(id); // xem ky lai doan nay trong java 8 ly thuyet cua thay truong
        if (telephoneOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay telephone mang id : " + id);
        }
        Telephone telephone = telephoneOptional.get();
        return objectMapper.convertValue(telephone, UpdateTelephoneRequest.class);
    }

    public void updateTelephone(UpdateTelephoneRequest updateBook) {
        Optional<Telephone> bookOptional = telephoneRepository.findById(updateBook.getId());
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay book mang Id : " + updateBook.getId());
        }

        Telephone telephone = bookOptional.get();
        TelephoneStatus currentStatus = telephone.getStatus();

        // cách 1 - làm bằng set thủ công
//        book.setName(updateBook.getName());
//        book.setPublishedAt(updateBook.getPublishedAt());
//        book.setPrice(updateBook.getPrice());
//        book.setRating(updateBook.getRating());
//        book.setAuthor(updateBook.getAuthor());
//        book.setPublisher(updateBook.getPublisher());

        // cách 2 - object mapper
        telephone = objectMapper.convertValue(updateBook, Telephone.class);
        telephone.setStatus(currentStatus);
        telephoneRepository.save(telephone);
    }

    public void changeTelephoneActivation(Long id) {
        Optional<Telephone> telephoneOptional = telephoneRepository.findById(id);
        if (telephoneOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy telephone mang Id: " + id);
        }

        Telephone telephone = telephoneOptional.get();
        telephone.setStatus(telephone.getStatus() == TelephoneStatus.ACTIVE ? TelephoneStatus.INACTIVE : TelephoneStatus.ACTIVE);
        telephoneRepository.save(telephone);
    }

    public List<TelephoneResponse> findActiveTelephone() {
        List<Telephone> telephones = telephoneRepository.findByStatus(TelephoneStatus.ACTIVE);
        return telephones
                .stream()
                .map(telephoneTemp -> objectMapper.convertValue(telephoneTemp, TelephoneResponse.class))
                .toList();
    }

    public Telephone findById(Long id) {
        Optional<Telephone> telephoneOptional = telephoneRepository.findById(id);
        if (telephoneOptional.isPresent()) {
            return telephoneOptional.get();
        } else {
            throw new ObjectNotFoundException("Không tìm thấy telephone với ID: " + id);
        }
    }
}
