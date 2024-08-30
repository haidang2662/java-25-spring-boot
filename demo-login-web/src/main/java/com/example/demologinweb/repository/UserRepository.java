package com.example.demologinweb.repository;

import com.example.demologinweb.entity.User;
import com.example.demologinweb.util.FileUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class UserRepository {

    private final FileUtil<User> fileUtil = new FileUtil<>();

    private List<User> findAll() {
        try {
            File file = new ClassPathResource("db/users.json").getFile();
            return fileUtil.readDataFromFile(file.getAbsolutePath(), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserByEmailAndPassword(String email, String password) {
        List<User> users = findAll();
        return users
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

}
