package com.example.examserver.services;

import com.example.examserver.custom.exceptions.UserAlreadyExistException;
import com.example.examserver.entities.Role;
import com.example.examserver.entities.User;
import com.example.examserver.entities.UserRole;
import com.example.examserver.interfaces.services.UserServiceInterface;
import com.example.examserver.repos.RoleRepository;
import com.example.examserver.repos.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> createUser(User user, Set<UserRole> userRoles) {
        ObjectMapper oMapper = new ObjectMapper();

        User usr=null;

        try {

            if(userRepository.findByUsername(user.getUsername())==null){
                for(UserRole role:userRoles){
                    Role arole=roleRepository.findByRole(role.getRole().getRole());
                    if(arole==null){
                        roleRepository.save(role.getRole());
                    }

                }
                //encrypting password
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.getUserRoles().addAll(userRoles);
                user.setEnabled(true);
                usr=userRepository.save(user);


            }else{
                throw new UserAlreadyExistException();


            }



        }catch (Exception e){
            e.printStackTrace();
            Map map=new HashMap();
            map.put("message",e.getMessage());

            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> map = oMapper.convertValue(usr, Map.class);
        return ResponseEntity.ok(map);
    }

    @Override
    public User updateUser(User user) {
        User usr=userRepository.findByUsername(user.getUsername());
        try{
            if(usr!=null){
                usr=userRepository.save(user);
            }
        }
        catch(Exception e)
        {e.printStackTrace();}
        return usr;
    }

    @Override
    public void deleteUser(Long id) {
        User usr=userRepository.findById(id).get();
        try{
            if(usr!=null){
                userRepository.delete(usr);
            }
        }
        catch(Exception e)
        {e.printStackTrace();}
    }

    @Override
    public User getUser(Long id) {
        User usr=null;
        try{
           usr=userRepository.findById(id).get();
        }
        catch(Exception e)
        {e.printStackTrace();}
        return usr;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        List<User> users=userRepository.findAll();
        return users;
    }

}
