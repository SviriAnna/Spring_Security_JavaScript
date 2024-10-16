package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleByName(String name);

    void save(Role role);
}
