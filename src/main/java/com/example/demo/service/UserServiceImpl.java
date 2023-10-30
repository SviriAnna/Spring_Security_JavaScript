package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserDao userDao;

    @Autowired
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    @Override
    @Transactional
    public void saveUser(User user) {
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
    @Transactional
    public void saveNewUser(User user){
        Role roleOfUser = roleDao.getRoleByName("ROLE_USER");
        user.addRole(roleOfUser);
        userDao.saveUser(user);
    }

    @Override
    public List<Role> getRolesList(){
        return userDao.getRolesList();
    }

}
