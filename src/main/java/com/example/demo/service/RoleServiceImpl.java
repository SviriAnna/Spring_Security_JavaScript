package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    public Role getRoleByName(String name) {
        try {
            return roleDao.getRoleByName(name);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void save(Role role){
        roleDao.save(role);
    }

}
