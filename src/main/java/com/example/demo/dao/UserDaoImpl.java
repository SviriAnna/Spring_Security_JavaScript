package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select distinct u FROM User u left join fetch u.roles", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    @Override
    public Optional<User> getUserByName(String username) throws NoResultException{
        User user = entityManager.createQuery(
                        "SELECT distinct u from User u LEFT JOIN FETCH u.roles WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
        return Optional.of(Optional.ofNullable(user).orElseThrow(() -> new EntityNotFoundException(String.format("User with username - '%s' not fount", username))));
    }

    @Override
    public HashSet<Role> getRolesList() {
        return new HashSet<Role>(entityManager.createQuery(
                "SELECT r from Role r", Role.class).getResultList());
    }


}
