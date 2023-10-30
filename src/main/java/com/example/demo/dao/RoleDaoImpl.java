package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private final EntityManager entityManager;


    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    public Role getRoleByName(String name) {
        return entityManager.createQuery(
                        "SELECT u from Role u WHERE u.name = :name", Role.class).
                setParameter("name", name).getSingleResult();
    }
}
