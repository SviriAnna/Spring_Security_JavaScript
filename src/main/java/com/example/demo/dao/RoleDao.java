package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.HashSet;
import java.util.List;

public interface RoleDao {
    HashSet<Role> getAllRoles();

    Role getRoleByName(String name);

    void save(Role role);
}