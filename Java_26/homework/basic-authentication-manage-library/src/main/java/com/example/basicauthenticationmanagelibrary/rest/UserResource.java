package com.example.basicauthenticationmanagelibrary.rest;

import com.example.basicauthenticationmanagelibrary.model.reponse.UserResponse;
import com.example.basicauthenticationmanagelibrary.model.request.UserRequest;
import com.example.basicauthenticationmanagelibrary.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserResource {

    UserService userService;

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getDetail(@PathVariable Long id) throws ClassNotFoundException {
        return userService.getDetail(id);
    }

    @PostMapping
    public UserResponse create(@RequestBody @Valid UserRequest request) {
        return userService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@RequestBody @Valid UserRequest request, @PathVariable("id") Long idUser) throws ClassNotFoundException {
        return userService.updateUser(request,idUser);
    }

    @GetMapping("/{id}/activations")
    public UserResponse changeUserActivation(@PathVariable Long id) throws ClassNotFoundException {
        return userService.changeUserActivation(id);
    }
}
