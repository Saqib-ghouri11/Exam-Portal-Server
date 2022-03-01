package com.example.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.examserver.entities.Role;
import com.example.examserver.entities.User;
import com.example.examserver.entities.UserRole;
import com.example.examserver.services.UserService;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExamServerApplication.class, args);
    }

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Server Started...");
//
//        User user=new User();
//        user.setFirstName("Saqib");
//        user.setLastName("Ghouri");
//        user.setEmail("saqib@example.com");
//        user.setEnabled(true);
//        user.setUsername("saqibali08");
//        user.setPassword("12341234");
//        user.setProfile("saqib.png");
//        user.setPhone("03022323223");
//        //setting role
//        Role role=new Role();
//        role.setRole("ADMIN");
//        role.setId(1L);
//
//        UserRole userRole=new UserRole();
//        //setting role in userRole Set
//        userRole.setRole(role);
//        userRole.setUser(user);
//
//
//        //adding role in userRoles set
//        Set<UserRole> userRoles=new HashSet<>();
//        userRoles.add(userRole);
//
//        user.getUserRoles().addAll(userRoles);
//
//        userService.createUser(user,userRoles);
      
    }
}
