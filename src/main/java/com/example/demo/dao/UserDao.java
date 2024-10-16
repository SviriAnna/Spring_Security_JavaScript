package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    public List<User> getAllUsers();
    public User getUserById(Long id);
    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    public Optional<User> getUserByName(@Param("username") String username);
    public void saveUser(User user);
    public User updateUser(User user);
    public void delete(Long id);
    public HashSet<Role> getRolesList();


}
