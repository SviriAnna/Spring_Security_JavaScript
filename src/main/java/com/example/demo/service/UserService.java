package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;

public interface UserService {
    User getUserById(Long id);
    User getUserByName(String username);
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(String id, User user, ArrayList<String> form);
    void delete(Long id);

    public HashSet<Role> getRolesList();

}
