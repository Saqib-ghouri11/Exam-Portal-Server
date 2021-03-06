package com.example.examserver.interfaces.services;

import com.example.examserver.entities.User;
import com.example.examserver.entities.UserRole;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserServiceInterface{
    public ResponseEntity<?> createUser(User user, Set<UserRole> userRole);
    public User updateUser(User user);
    public void deleteUser(Long id);
    public User getUser(Long id);
    public User getByUsername(String username);
    public List<User> getUsers();
}
