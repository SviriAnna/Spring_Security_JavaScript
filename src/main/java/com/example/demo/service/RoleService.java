package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleService {
    HashSet<Role> getAllRoles();

    Role getRoleByName(String name);

    public void save(Role role);
}
