package com.example.homework01.service;

import com.example.homework01.EmailExistedException;
import com.example.homework01.constant.UserStatus;
import com.example.homework01.entity.User;
import com.example.homework01.exceptionHandling.ObjectNotFoundException;
import com.example.homework01.model.request.LoginRequest;
import com.example.homework01.model.request.RegisterRequest;
import com.example.homework01.model.request.UpdateUserRequest;
import com.example.homework01.model.response.UserResponse;
import com.example.homework01.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
        user.setStatus(UserStatus.ACTIVE);

        // Lưu đối tượng User vào repository
        userRepository.save(user);
    }

    public User login(LoginRequest user) {
        return userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
//        List<UserResponse> result = new ArrayList<>();
//        for (User user : users) {
//            // Cach 1: Lam thu cong
////            UserResponse userResponse = new UserResponse();
////            userResponse.setId(user.getId());
////            userResponse.setEmail(user.getEmail());
//            //......
//
//
//            // Cach 2: Su dung objectMapper
//            UserResponse userResponse = objectMapper.convertValue(user, UserResponse.class);
//
//            // Cach 3: su dung builder
////            UserResponse userResponse = UserResponse.builder()
////                    .id(user.getId())
////                    .name(user.getName())
////                    .dateOfBirth(user.getDateOfBirth())
////                    .email(user.getEmail())
////                    .hometown(user.getHometown())
////                    .gender(user.getGender())
////                    .build();
//
//            result.add(userResponse);
//        }
//        return result;
        return users.stream().map(u -> objectMapper.convertValue(u, UserResponse.class)).toList();
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
}
