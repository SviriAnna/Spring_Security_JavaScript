package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
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
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void saveUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRoles().isEmpty()) {
            user.getRoles().add(roleDao.getRoleByName("ROLE_USER"));
        }

        userDao.saveUser(user);
    }

    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Optional<User> getUserByName(String username) {
        try {
            return userDao.getUserByName(username);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public HashSet<Role> getRolesList() {
        return userDao.getRolesList();
    }

}