package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;

public interface UserService {
    User getUserById(Long id);
    Optional<User> getUserByName(String username);
    List<User> getAllUsers();
    void saveUser(User user);
    User updateUser(User user);
    void delete(Long id);

    public HashSet<Role> getRolesList();

}
