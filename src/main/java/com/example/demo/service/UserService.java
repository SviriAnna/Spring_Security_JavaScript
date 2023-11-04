package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User getUserById(Long id);
    User getUserByName(String username);
    List<User> getAllUsers();
    void saveUser(String id, User user, Map<String, String> form);
    void delete(Long id);

    public void saveNewUser(User user);

    public HashSet<Role> getRolesList();

}
