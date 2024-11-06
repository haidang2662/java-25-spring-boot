package org.example.jwtauthenticationspring3.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.jwtauthenticationspring3.entity.Role;
import org.example.jwtauthenticationspring3.entity.User;
import org.example.jwtauthenticationspring3.exception.ObjectNotFoundException;
import org.example.jwtauthenticationspring3.exception.UserExistedException;
import org.example.jwtauthenticationspring3.model.request.RegistrationRequest;
import org.example.jwtauthenticationspring3.model.response.UserResponse;
import org.example.jwtauthenticationspring3.repository.RoleRepository;
import org.example.jwtauthenticationspring3.repository.UserRepository;
import org.example.jwtauthenticationspring3.statics.Roles;
import org.example.jwtauthenticationspring3.statics.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    RoleRepository roleRepository;

    ObjectMapper objectMapper;

    public UserResponse registerUser(RegistrationRequest registrationRequest) throws ObjectNotFoundException, UserExistedException {
        Role role = roleRepository.findByName(Roles.USER)
                .orElseThrow(() -> new ObjectNotFoundException("Cannot find USER role"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Optional<User> userOptional = userRepository.findByEmail(registrationRequest.getEmail());
        if(userOptional.isPresent()){
            throw new UserExistedException("Email nay da ton tai trong he thong");
        }
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .email(registrationRequest.getEmail())
                .phone(registrationRequest.getPhone())
                .address(registrationRequest.getAddress())
                .roles(roles)
                .status(UserStatus.INACTIVE)
                .build();
        userRepository.save(user);
        return objectMapper.convertValue(user, UserResponse.class);
    }


    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            return users.stream()
                    .map(u -> objectMapper.convertValue(u, UserResponse.class))
                    .toList();
        }
        return Collections.emptyList();
    }

    public UserResponse getDetail(Long id) throws ObjectNotFoundException {
        return userRepository
                .findById(id)
                .map(u -> objectMapper.convertValue(u, UserResponse.class))
                .orElseThrow(() -> new ObjectNotFoundException("Cannot find user with id: " + id));
    }


    public UserResponse updateUser(RegistrationRequest request, Long idUser) throws ObjectNotFoundException, UserExistedException {

        Optional<User> userOptional = userRepository.findById(idUser);
        if(userOptional.isEmpty()){
            throw new ObjectNotFoundException("Khong tim thay User co id la : " +idUser);
        }
        Optional<User> userOptional1 = userRepository.findByEmail(request.getEmail());
        if(userOptional1.isPresent()){
            throw new UserExistedException("Email nay da ton tai trong he thong");
        }
        User user = userOptional.get();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        userRepository.save(user);
        return objectMapper.convertValue(user , UserResponse.class);
    }

    public UserResponse changeStatus(Long id) throws ObjectNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new ObjectNotFoundException("Khong tim thay User co id la : " +id);
        }
        User user = userOptional.get();
        if(user.getStatus() == UserStatus.ACTIVE){
            user.setStatus(UserStatus.INACTIVE);
        } else {
            user.setStatus(UserStatus.ACTIVE);
        }
        userRepository.save(user);
        return objectMapper.convertValue(user, UserResponse.class);
    }
}
