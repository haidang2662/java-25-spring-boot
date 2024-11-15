package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.techmaster.danglh.recruitmentproject.constant.Roles;
import vn.techmaster.danglh.recruitmentproject.dto.SearchUserDto;
import vn.techmaster.danglh.recruitmentproject.entity.User;
import vn.techmaster.danglh.recruitmentproject.exception.ExistedUserException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.CreateUserRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.UserSearchRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.CommonSearchResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.UserResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.UserSearchResponse;
import vn.techmaster.danglh.recruitmentproject.repository.UserRepository;
import vn.techmaster.danglh.recruitmentproject.repository.custom.UserCustomRepository;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final PasswordEncoder passwordEncoder;

    final UserRepository userRepository;


    final ObjectMapper objectMapper;

    final UserCustomRepository userCustomRepository;

    public UserResponse getDetail(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id)
                .map(u -> objectMapper.convertValue(u, UserResponse.class))
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }


    public UserResponse createUser(CreateUserRequest request) throws ExistedUserException {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            throw new ExistedUserException("Username existed");
        }


        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode("123")) // TODO: change to random password
                .role(Roles.CANDIDATE) // TODO : Đoạn này chưa hiểu lắm làm thế nào để chọn candidate hay company
                .status(AccountStatus.ACTIVATED)
                .build();
        userRepository.save(user);
        return objectMapper.convertValue(user, UserResponse.class);
    }

    public CommonSearchResponse<UserSearchResponse> searchUser(UserSearchRequest request) {
        List<SearchUserDto> result = userCustomRepository.searchUser(request);

        Long totalRecord = 0L;
        List<UserSearchResponse> studentResponses = new ArrayList<>();
        if (!result.isEmpty()) {
            totalRecord = result.get(0).getTotalRecord();
            studentResponses = result
                    .stream()
                    .map(s -> objectMapper.convertValue(s, UserSearchResponse.class))
                    .toList();
        }

        int totalPage = (int) Math.ceil((double) totalRecord / request.getPageSize());

        return CommonSearchResponse.<UserSearchResponse>builder()
                .totalRecord(totalRecord)
                .totalPage(totalPage)
                .data(studentResponses)
                .pageInfo(new CommonSearchResponse.CommonPagingResponse(request.getPageSize(), request.getPageIndex()))
                .build();
    }

}
