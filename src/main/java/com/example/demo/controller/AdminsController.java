package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
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
    private String getAllUsers(@RequestParam(defaultValue = "0") Long id, Model model) {
        if(id==0) {
            model.addAttribute("users", userService.getAllUsers());
        } else {
            model.addAttribute("users", userService.getUserById(id));
        }
        return "allUsers";
    }

    @GetMapping("/id={id}")
    private String view(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/save")
    private String save(@ModelAttribute("user") User user,
                        @RequestParam Map<String, String> form,
                        @RequestParam("id") String id) {

        userService.saveUser(id, user, form);

        return "redirect:/admin";
    }

    @GetMapping("/edit/id={id}")
    private String editUser(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());

        return "new_or_edit_user";
    }

    @GetMapping("/new")
    private String creatureUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());

        return "new_or_edit_user";
    }

    @GetMapping("/delete/id={id}")
    private String delete(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
