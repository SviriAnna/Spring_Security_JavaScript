package com.example.demo.dao;

import com.example.demo.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = entityManager.createQuery("select c FROM User c").getResultList();
        return userList;
    }
    @Override
    public User showUserById(int id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void saveUser(User user) {
        if(user.getId() == 0){
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }
    @Override
    public void delete(int id) {
        entityManager.remove(showUserById(id));
    }
}
