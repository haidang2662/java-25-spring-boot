package vn.techmaster.danglh.recruitmentproject.config;

import vn.techmaster.danglh.recruitmentproject.constant.Role;
import vn.techmaster.danglh.recruitmentproject.entity.Account;
import vn.techmaster.danglh.recruitmentproject.repository.AccountRepository;
import vn.techmaster.danglh.recruitmentproject.constant.Constant;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInitializer implements CommandLineRunner {

    AccountRepository accountRepository;


    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Optional<Account> admin = accountRepository.findByEmail("admin");
        if (admin.isEmpty()) {
            Account account = new Account();
            account.setEmail("admin"); // nen de thang hang so o yml
            account.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
            account.setRole(Role.ADMIN);
            account.setStatus(AccountStatus.ACTIVATED);
            account.setCreatedBy(Constant.DEFAULT_CREATOR);
            account.setLastModifiedBy(Constant.DEFAULT_CREATOR);
            accountRepository.save(account);
        }
    }
}


