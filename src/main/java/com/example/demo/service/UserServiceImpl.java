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
    public void updateUser(String idString, User user, ArrayList<String> form) {

        Long id = Long.valueOf(idString);
        user.setId(id);
        String a = user.getPassword();
        String b = (userDao.getUserById(id)).getPassword();

        if(!a.equals(b)){
            user.setPassword(encoder.encode(user.getPassword()));
        }

        Set<String> roles = Arrays.stream(roleDao.getAllRoles().toArray())
                .map(Object::toString)
                .collect(Collectors.toSet());

        for (String value : form) {
            if (roles.contains(value)) {
                user.getRoles().add(roleDao.getRoleByName(value));
            }
        }

        if (user.getRoles().isEmpty()) {
            user.getRoles().add(roleDao.getRoleByName("ROLE_USER"));
        }

        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void saveUser(User user, ArrayList<String> form) {

        user.setPassword(encoder.encode(user.getPassword()));

        Set<String> roles = Arrays.stream(roleDao.getAllRoles().toArray())
                .map(Object::toString)
                .collect(Collectors.toSet());

        for (String value : form) {
            if (roles.contains(value)) {
                user.getRoles().add(roleDao.getRoleByName(value));
            }
        }

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