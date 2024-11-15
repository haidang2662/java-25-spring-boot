package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.techmaster.danglh.recruitmentproject.constant.Role;
import vn.techmaster.danglh.recruitmentproject.entity.Account;
import vn.techmaster.danglh.recruitmentproject.entity.RefreshToken;
import vn.techmaster.danglh.recruitmentproject.exception.ExistedUserException;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidRefreshTokenException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.LoginRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.RefreshTokenRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.RegistrationRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.JwtResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.AccountResponse;
import vn.techmaster.danglh.recruitmentproject.repository.RefreshTokenRepository;
import vn.techmaster.danglh.recruitmentproject.repository.AccountRepository;
import vn.techmaster.danglh.recruitmentproject.security.CustomUserDetails;
import vn.techmaster.danglh.recruitmentproject.security.JwtService;
import vn.techmaster.danglh.recruitmentproject.security.SecurityUtils;
import vn.techmaster.danglh.recruitmentproject.constant.Constant;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationService {

    final JwtService jwtService;

    final AccountRepository accountRepository;

    final RefreshTokenRepository refreshTokenRepository;

    final AuthenticationManager authenticationManager;


    final PasswordEncoder passwordEncoder;

    final ObjectMapper objectMapper;

    final EmailService emailService;

    @Value("${application.security.refreshToken.tokenValidityMilliseconds}")
    long refreshTokenValidityMilliseconds;

    public AccountResponse registerUser(RegistrationRequest registrationRequest)
            throws ObjectNotFoundException, ExistedUserException, MessagingException {
        Optional<Account> userOptional = accountRepository.findByEmailAndStatus(registrationRequest.getUsername(), AccountStatus.ACTIVATED);
        if (userOptional.isPresent()) {
            throw new ExistedUserException("Username existed");
        }
        Account account = Account.builder()
                .email(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(Role.CANDIDATE) // Todo : đoạn này chưa hiểu lắm làm thế nào để phân biệt là  candidate hay company
                .status(AccountStatus.CREATED)
                .createdBy(Constant.DEFAULT_CREATOR)
                .lastModifiedBy(Constant.DEFAULT_CREATOR)
                .activationMailSentAt(LocalDateTime.now())
                .activationMailSentCount(1)
                .build();
        accountRepository.save(account);

        emailService.sendActivationMail(account);

        return objectMapper.convertValue(account, AccountResponse.class);
    }

    public JwtResponse authenticate(LoginRequest request) throws ObjectNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Account account = accountRepository.findById(userDetails.getId())
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

//        String refreshToken = UUID.randomUUID().toString();
        String refreshToken = jwtService.generateJwtRefreshToken(authentication);
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .refreshToken(refreshToken)
                .account(account)
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        return JwtResponse.builder()
                .jwt(jwt)
                .refreshToken(refreshToken)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .roles(roles)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public JwtResponse refreshToken(RefreshTokenRequest request) throws InvalidRefreshTokenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        JwtResponse response = accountRepository.findById(userDetails.getId())
                .flatMap(user -> refreshTokenRepository
                        .findByAccountAndRefreshTokenAndInvalidated(user, request.getRefreshToken(), false)
                        .map(oldRefreshToken -> {
                            LocalDateTime createdDateTime = oldRefreshToken.getCreatedAt();
                            LocalDateTime expiryTime = createdDateTime.plusSeconds(refreshTokenValidityMilliseconds / 1000);
                            if (expiryTime.isBefore(LocalDateTime.now())) {
                                oldRefreshToken.setInvalidated(true);
                                refreshTokenRepository.save(oldRefreshToken);
                                return null;
                            }
                            String jwtToken = jwtService.generateJwtToken(authentication);
                            String refreshToken = jwtService.generateJwtRefreshToken(authentication);
                            RefreshToken refreshTokenEntity = RefreshToken.builder()
                                    .refreshToken(refreshToken)
                                    .account(user)
                                    .build();
                            refreshTokenRepository.save(refreshTokenEntity);
                            oldRefreshToken.setInvalidated(true);
                            refreshTokenRepository.save(oldRefreshToken);
                            return JwtResponse.builder()
                                    .jwt(jwtToken)
                                    .refreshToken(refreshToken)
                                    .id(userDetails.getId())
                                    .username(userDetails.getUsername())
                                    .roles(userDetails.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .collect(Collectors.toSet()))
                                    .build();
                        }))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (response == null) {
            throw new InvalidRefreshTokenException("Refresh token invalid or expired");
        }
        return response;
    }

    @Transactional
    public void logout() {
        Long userId = SecurityUtils.getCurrentUserLoginId()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        refreshTokenRepository.logOut(userId);
        SecurityContextHolder.clearContext();
    }

}
