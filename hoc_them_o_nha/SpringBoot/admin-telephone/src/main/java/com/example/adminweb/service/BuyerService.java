package com.example.adminweb.service;

import com.example.adminweb.exceptionHandling.EmailExistedException;
import com.example.adminweb.constant.BuyerStatus;
import com.example.adminweb.entity.Buyer;
import com.example.adminweb.exceptionHandling.ObjectNotFoundException;
import com.example.adminweb.model.request.RegisterRequest;
import com.example.adminweb.model.request.UpdateBuyerRequest;
import com.example.adminweb.model.response.BuyerResponse;
import com.example.adminweb.repository.BuyerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyerService {

    BuyerRepository buyerRepository;

    ObjectMapper objectMapper;

    public void register(RegisterRequest registerRequest) throws EmailExistedException {
        /**
         * check các dữu liệu đầu vào mà Spring validation không làm được
         *   ==>  ngày sinh (phải trên 18 tuổi), email chưa tồn tại trong hệ thống, số điện thoại chưa tồn tại trong hệ thống
         */

        // check email chưa tồn tại trong hệ thống thì cho đi tiếp
        Optional<Buyer> userOptional = buyerRepository.findByEmailIgnoreCase(registerRequest.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailExistedException("Email đã tồn tại");
        }

        // Tạo đối tượng User từ RegisterRequest
//        User user = new User();
//        user.setEmail(registerRequest.getEmail());
//        user.setPassword(registerRequest.getPassword());
//        user.setName(registerRequest.getName());
//        user.setDateOfBirth(registerRequest.getDateOfBirth());
//        user.setGender(registerRequest.getGender());
//        user.setHometown(registerRequest.getHometown());

        Buyer user = objectMapper.convertValue(registerRequest, Buyer.class);
        // Lưu đối tượng User vào repository
        buyerRepository.save(user);
    }

    public Page<BuyerResponse> findAllUsers(int page , int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<Buyer> userPage = buyerRepository.findAll(pageable);

        // 2 - convert từ entity sang response
        List<Buyer> users = userPage.getContent();

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
        List<BuyerResponse> buyerRespons = users
                .stream()
                .map(userTemp -> objectMapper.convertValue(userTemp, BuyerResponse.class))
                .toList();

        // 3 - trả về kết quả
        return new PageImpl<>(buyerRespons, pageable, userPage.getTotalElements());
    }

    public void deleteByIdBuyer(long id) {
        buyerRepository.deleteById(id);
    }

    public UpdateBuyerRequest findBuyerById(long id) {

        Optional<Buyer> buyerOptional = buyerRepository.findById(id);
        if (buyerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy buyer mang Id: " + id);
        }

        Buyer buyer = buyerOptional.get();
        return objectMapper.convertValue(buyer, UpdateBuyerRequest.class);
    }

    public void updateBuyer(UpdateBuyerRequest buyerUpdate) {
//        List<User> users = userRepository.findAll();
//        users.forEach(s -> {
//            if (s.getId() != buyerUpdate.getId()) {
//                return;
//            }
//            s.setEmail(buyerUpdate.getEmail());
//            s.setName(buyerUpdate.getEmail());
//            s.setDateOfBirth(buyerUpdate.getDateOfBirth());
//            s.setGender(buyerUpdate.getGender());
//            s.setHometown(buyerUpdate.getHometown());
//        });
//        userRepository.saveAll(users);

        Optional<Buyer> buyerOptional = buyerRepository.findById(buyerUpdate.getId());
        if (buyerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy buyer mang Id: " + buyerUpdate.getId());
        }

        Buyer buyer = buyerOptional.get();
        buyer.setName(buyerUpdate.getName());
        buyer.setEmail(buyerUpdate.getEmail());
        buyer.setHometown(buyerUpdate.getHometown());
        buyer.setGender(buyerUpdate.getGender());
        buyer.setDateOfBirth(buyerUpdate.getDateOfBirth());

        buyerRepository.save(buyer);
    }

    public void changeBuyerActivation(Long id) {
        Optional<Buyer> buyerOptional = buyerRepository.findById(id);
        if (buyerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy buyer mang Id: " + id);
        }

        Buyer buyer = buyerOptional.get();
        buyer.setStatus(buyer.getStatus() == BuyerStatus.ACTIVE ? BuyerStatus.INACTIVE : BuyerStatus.ACTIVE);
        buyerRepository.save(buyer);
    }

    public List<BuyerResponse> findActiveBuyer() {
        List<Buyer> buyers = buyerRepository.findByStatus(BuyerStatus.ACTIVE);
        return buyers
                .stream()
                .map(buyerTemp -> objectMapper.convertValue(buyerTemp, BuyerResponse.class))
                .toList();
    }

//    public User findUserById(Long readerId) {
//        Optional<User> userOptional = userRepository.findById(readerId);
//        if (userOptional.isPresent()) {
//            return userOptional.get();
//        } else {
//            throw new ObjectNotFoundException("Không tìm thấy bạn đọc với ID: " + readerId);
//        }
//    }
}
