package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("")
public class UsersController {


    private final UserService userService;


    private final RoleService roleService;

    private UsersController(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/api/admin/users")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/admin/users/{id}")
    private User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/info")
    public ResponseEntity<User> getCurrent(Principal principal) {
        return new ResponseEntity<>(userService.getUserByName(principal.getName()).get(), HttpStatus.OK);
    }

    @GetMapping("/api/admin/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping("/api/admin/users")
    private ResponseEntity<User> save(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/users/{id}")
    private User updateUser(@PathVariable Long id, @RequestBody @Valid User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/api/admin/users/{id}")
    private String delete(@PathVariable Long id){
        userService.delete(id);
        return "User with ID "+ id + " was deleted.";
    }

}
