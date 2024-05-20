package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoleService roleService;

    @Autowired
    private final PasswordEncoder encoder;

    private AdminsController(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping()
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    private User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    private void save(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/{id}")
    private User updateUser(@RequestBody User user){
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id){
        userService.delete(id);
        return "User with ID "+ id + " was deleted.";
    }

}
