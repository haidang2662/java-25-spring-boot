package com.example.adminweb.service;

import com.example.adminweb.exceptionHandling.EmailExistedException;
import com.example.adminweb.constant.UserStatus;
import com.example.adminweb.entity.User;
import com.example.adminweb.exceptionHandling.ObjectNotFoundException;
import com.example.adminweb.model.request.RegisterRequest;
import com.example.adminweb.model.request.UpdateUserRequest;
import com.example.adminweb.model.response.UserResponse;
import com.example.adminweb.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    ObjectMapper objectMapper;

    public void register(RegisterRequest registerRequest) throws EmailExistedException {
        /**
         * check các dữu liệu đầu vào mà Spring validation không làm được
         *   ==>  ngày sinh (phải trên 18 tuổi), email chưa tồn tại trong hệ thống, số điện thoại chưa tồn tại trong hệ thống
         */

        // check email chưa tồn tại trong hệ thống thì cho đi tiếp
        Optional<User> userOptional = userRepository.findByEmailIgnoreCase(registerRequest.getEmail());
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

        User user = objectMapper.convertValue(registerRequest, User.class);
        // Lưu đối tượng User vào repository
        userRepository.save(user);
    }

    public Page<UserResponse> findAllUsers(int page , int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<User> userPage = userRepository.findAll(pageable);

        // 2 - convert từ entity sang response
        List<User> users = userPage.getContent();

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
        List<UserResponse> userResponses = users
                .stream()
                .map(userTemp -> objectMapper.convertValue(userTemp, UserResponse.class))
                .toList();

        // 3 - trả về kết quả
        return new PageImpl<>(userResponses, pageable, userPage.getTotalElements());
    }

    public void deleteByIdUser(long id) {
        userRepository.deleteById(id);
    }

    public UpdateUserRequest findUserById(long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy user mang Id: " + id);
        }

        User user = userOptional.get();
        return objectMapper.convertValue(user, UpdateUserRequest.class);
    }

    public void updateUser(UpdateUserRequest userUpdate) {
//        List<User> users = userRepository.findAll();
//        users.forEach(s -> {
//            if (s.getId() != userUpdate.getId()) {
//                return;
//            }
//            s.setEmail(userUpdate.getEmail());
//            s.setName(userUpdate.getEmail());
//            s.setDateOfBirth(userUpdate.getDateOfBirth());
//            s.setGender(userUpdate.getGender());
//            s.setHometown(userUpdate.getHometown());
//        });
//        userRepository.saveAll(users);

        Optional<User> userOptional = userRepository.findById(userUpdate.getId());
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy user mang Id: " + userUpdate.getId());
        }

        User user = userOptional.get();
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setHometown(userUpdate.getHometown());
        user.setGender(userUpdate.getGender());
        user.setDateOfBirth(userUpdate.getDateOfBirth());

        userRepository.save(user);
    }

    public void changeUserActivation(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy user mang Id: " + id);
        }

        User user = userOptional.get();
        user.setStatus(user.getStatus() == UserStatus.ACTIVE ? UserStatus.INACTIVE : UserStatus.ACTIVE);
        userRepository.save(user);
    }

    public List<UserResponse> findActiveUser() {
        List<User> users = userRepository.findByStatus(UserStatus.ACTIVE);
        return users
                .stream()
                .map(userTemp -> objectMapper.convertValue(userTemp, UserResponse.class))
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
