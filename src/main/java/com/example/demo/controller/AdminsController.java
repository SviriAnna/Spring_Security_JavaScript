package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoleService roleService;

    private AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
    private String save(@RequestParam String username,
                        @RequestParam String name,
                        @RequestParam String lastname,
                        @RequestParam int age,
                        @RequestParam String password,
                        @RequestParam Map<String, String> form,
                        @RequestParam("id") String id) {
        User user = new User();
        if(!Objects.equals(id, "")){
            user = userService.getUserById(Long.valueOf(id));
        }
        user.setUsername(username);
        user.setName(name);
        user.setLastname(lastname);
        user.setAge(age);
        user.setPassword(password);

        Set<String> roles = Arrays.stream(roleService.getAllRoles().toArray())
                .map(Object::toString)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }

        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/edit/id={id}")
    private String editUser(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());

        return "creature_and_edit_user";
    }

    @GetMapping("/new")
    private String editUser(Model model) {
        User user = new User();
        model.addAttribute("user", user
        );
        model.addAttribute("roles", roleService.getAllRoles());

        return "creature_and_edit_user";
    }

    @GetMapping("/delete/id={id}")
    private String delete(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
