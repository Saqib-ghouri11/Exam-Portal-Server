package com.example.examserver.controllers.rest;

import com.example.examserver.entities.Role;
import com.example.examserver.entities.User;
import com.example.examserver.entities.UserRole;
import com.example.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestController {

    @Autowired
    UserService userService;
    @PostMapping("/post")
    public User createUser(@RequestBody User user){
        Set<UserRole> userRoleSet=new HashSet<UserRole>();
        Role role=new Role();
        role.setId(2L);
        role.setRole("NORMAL");
        userRoleSet.add(new UserRole(user,role));
        return userService.createUser(user,userRoleSet);
    }

    @PutMapping("/put")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable("username") String username){
        return userService.getByUsername(username);
    }

    @GetMapping("")
    public User getById(@RequestParam(name = "id") Long id){
        return userService.getUser(id);
    }

}
