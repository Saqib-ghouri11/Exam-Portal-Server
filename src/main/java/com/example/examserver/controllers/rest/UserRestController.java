package com.example.examserver.controllers.rest;

import com.example.examserver.custom.exceptions.UserAlreadyExistException;
import com.example.examserver.entities.Role;
import com.example.examserver.entities.User;
import com.example.examserver.entities.UserRole;
import com.example.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRestController {

    @Autowired
    UserService userService;
    @PostMapping("/post")
    public User createUser(@RequestBody User user){

            Set<UserRole> userRoleSet = new HashSet<UserRole>();
            Role role = new Role();
            role.setId(2L);
            role.setRole("NORMAL");
            userRoleSet.add(new UserRole(user, role));
            User usr= userService.createUser(user, userRoleSet);

        return usr;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserAlreadyExistException.class,MethodArgumentNotValidException.class})
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,UserAlreadyExistException usr) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Arrays.stream(usr.getStackTrace()).forEach(error->errors.put("error",usr.getMessage()));

        return errors;
    }

}
