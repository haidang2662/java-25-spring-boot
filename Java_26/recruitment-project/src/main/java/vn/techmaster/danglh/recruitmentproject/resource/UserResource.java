package vn.techmaster.danglh.recruitmentproject.resource;

import vn.techmaster.danglh.recruitmentproject.exception.ExistedUserException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.CreateAccountRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.AccountSearchRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.CommonSearchResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.AccountResponse;
import vn.techmaster.danglh.recruitmentproject.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserResource {

    UserService userService;

    @GetMapping
    public CommonSearchResponse<?> search(AccountSearchRequest request) {
        return userService.searchUser(request);
    }

    @GetMapping("/{id}")
    public AccountResponse getDetail(@PathVariable Long id) throws ObjectNotFoundException {
        return userService.getDetail(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountRequest request) throws ExistedUserException {
        AccountResponse accountResponse = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(accountResponse);
    }

}
