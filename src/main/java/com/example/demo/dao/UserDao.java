package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);
    User showUserById(int id);
    void delete(int id);
}
