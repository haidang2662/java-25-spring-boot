package org.example.jwtauthenticationspring3.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.jwtauthenticationspring3.exception.ObjectNotFoundException;
import org.example.jwtauthenticationspring3.model.request.UserRequest;
import org.example.jwtauthenticationspring3.model.response.UserResponse;
import org.example.jwtauthenticationspring3.repository.UserRepository;
import org.example.jwtauthenticationspring3.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserResource {

    UserService userService;

    UserRepository userRepository;

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getDetail(@PathVariable Long id) throws ObjectNotFoundException {
        return userService.getDetail(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}/changestatus")
    public UserResponse changeStatus(@PathVariable Long id) throws ObjectNotFoundException {
        return userService.changeStatus(id);
    }

}
