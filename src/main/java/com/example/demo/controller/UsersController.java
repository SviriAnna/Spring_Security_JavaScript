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


@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private final UserService userService;

    private UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
    }

    @GetMapping()
    private String getUser(Principal principal, Model model) {
            model.addAttribute("user", userService.getUserByName(principal.getName()));

        return "user";
    }

}
