package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    private UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    private String getAllUsers(@RequestParam(defaultValue = "0") int id, Model model) {
        if(id==0) {
            model.addAttribute("users", userService.getAllUsers());
        } else {
            model.addAttribute("users", userService.showUserById(id));
        }
        return "someUsers";
    }
    @GetMapping (value = "/new")
    private String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "edit";
    }
    @PostMapping("/save")
    private String save(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/edit")
    private String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.showUserById(id));
        return "edit";
    }
    @GetMapping("/delete")
    private String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
