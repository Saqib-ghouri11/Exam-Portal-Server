package com.example.examserver.controllers.rest;

import com.example.examserver.pojos.JwtRequest;
import com.example.examserver.pojos.JwtResponse;
import com.example.examserver.services.CustomUserDetailsService;
import com.example.examserver.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest.toString());
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Bad Credentials.");
        }
        String token= jwtUtil.generateToken(customUserDetailsService.loadUserByUsername(jwtRequest.getUsername()));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
