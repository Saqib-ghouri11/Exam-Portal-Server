package com.example.examserver;

import com.example.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//        user.setPassword("1234");
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
//        User usr=userService.createUser(user,userRoles);
//        System.out.println(usr.getUsername());
    }
}
