package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserDao userDao;

    @Autowired
    private final RoleDao roleDao;

    @Autowired
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(String idString, User user, Map<String, String> form) {
        User savedUser = new User();
        if(!Objects.equals(idString, "")) {
            Long id = Long.valueOf(idString);
            savedUser = userDao.getUserById(id);
            savedUser.setId(id);
            if (Objects.equals(user.getPassword(), savedUser.getPassword())) {
                savedUser.setPassword(user.getPassword());
            } else {
                savedUser.setPassword(encoder.encode(user.getPassword()));
            }
        } else {
            savedUser.setPassword(encoder.encode(user.getPassword()));
        }
        savedUser.setUsername(user.getUsername());
        savedUser.setName(user.getName());
        savedUser.setLastname(user.getLastname());
        savedUser.setAge(user.getAge());


        Set<String> roles = Arrays.stream(roleDao.getAllRoles().toArray())
                .map(Object::toString)
                .collect(Collectors.toSet());

        savedUser.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                savedUser.getRoles().add(roleDao.getRoleByName(key));
            }
        }

        if (savedUser.getRoles().isEmpty()) {
            savedUser.getRoles().add(roleDao.getRoleByName("ROLE_USER"));
        }

        userDao.saveUser(savedUser);
    }

    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getUserById(Long id){
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String username) { return userDao.getUserByName(username); }

    @Override
    public HashSet<Role> getRolesList(){
        return userDao.getRolesList();
    }

}
