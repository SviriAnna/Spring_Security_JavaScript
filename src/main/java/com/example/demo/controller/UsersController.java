package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private final UserService userService;

    private UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    private User getUser(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

}
