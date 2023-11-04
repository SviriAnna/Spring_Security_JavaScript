package com.example.demo.Init;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@EnableTransactionManagement
@Component
public class AppInit implements ApplicationListener<ContextRefreshedEvent> {

    private RoleService roleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AppInit(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, String> mapOfRole = new HashMap<>();
        if (roleService.getRoleByName("ROLE_USER") == null) {
            Role userRole = new Role("ROLE_USER");
            roleService.save(userRole);
        }
        if (roleService.getRoleByName("ROLE_ADMIN") == null) {
            Role adminRole = new Role("ROLE_ADMIN");
            roleService.save(adminRole);
        }
        if (userService.getUserByName("user") == null) {
            User user = new User("user", "userName", "userLastname", 20, "user", new HashSet<Role>());
            user.setPassword(passwordEncoder.encode("user"));
            mapOfRole.clear();
            mapOfRole.put("ROLE_USER", "user");
            userService.saveUser("",user, mapOfRole);
        }
        if (userService.getUserByName("admin") == null) {
            User admin = new User("admin", "adminName", "adminLastname", 20, "admin", new HashSet<Role>());

            mapOfRole.clear();
            mapOfRole.put("ROLE_ADMIN", "admin");
            userService.saveUser("",admin, mapOfRole);
        }
    }

}
