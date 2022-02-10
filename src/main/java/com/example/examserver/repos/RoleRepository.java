package com.example.examserver.repos;

import com.example.examserver.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(String role);

}
