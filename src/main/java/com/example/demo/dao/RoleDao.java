package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRoleByName(String name);
}