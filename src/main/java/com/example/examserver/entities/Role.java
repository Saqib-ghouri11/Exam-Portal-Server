package com.example.examserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    private Long id;
    private String role;

    public Role(String role){
        this.role=role;
    }
    public Role(Long id,String role){
        this.role=role;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "role")
    private Set<UserRole> userRoles=new HashSet<>();
}
