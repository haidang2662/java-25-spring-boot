package com.example.managelibrary.service;

import com.example.managelibrary.constant.UserStatus;
import com.example.managelibrary.entity.User;
import com.example.managelibrary.exceptionHandling.EmailExistedException;
import com.example.managelibrary.exceptionHandling.ObjectNotFoundException;
import com.example.managelibrary.model.request.RegisterRequest;
import com.example.managelibrary.model.request.UpdateUserRequest;
import com.example.managelibrary.model.response.UserResponse;
import com.example.managelibrary.repository.UserRepository;
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

        Optional<User> userOptional = userRepository.findByEmailIgnoreCase(registerRequest.getEmail());

        if (userOptional.isPresent()) {
            throw new EmailExistedException("Email đã tồn tại");
        }

        User user = objectMapper.convertValue(registerRequest , User.class);
        userRepository.save(user);
    }

    public Page<UserResponse> findAllUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize, Sort.by("id").descending());
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();

        List<UserResponse> userResponses = users
                .stream()
                .map(userTemp -> objectMapper.convertValue(userTemp , UserResponse.class))
                .toList();

        return new PageImpl<>(userResponses , pageable , userPage.getTotalElements());

    }

    public void deleteByIdUser(Long id) {
        userRepository.deleteById(id);
    }

    public UpdateUserRequest findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy user mang Id: " + id);
        }

        User user = userOptional.get();
        return objectMapper.convertValue(user, UpdateUserRequest.class);
    }

    public void updateUser(UpdateUserRequest userUpdate) {
        Optional<User> userOptional = userRepository.findById(userUpdate.getId());
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy user mang Id: " + userUpdate.getId());
        }

        User user = userOptional.get();
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setHometown(userUpdate.getHometown());
        user.setGender(userUpdate.getGender());
        user.setDob(userUpdate.getDob());

        userRepository.save(user);
    }

    public void changeUserActivation(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy user mang Id: " + id);
        }

        User user = userOptional.get();
        user.setStatus(user.getStatus() == UserStatus.Valid ? UserStatus.Invalid : UserStatus.Valid);
        userRepository.save(user);
    }
}
