package com.example.examserver.repos;

import com.example.examserver.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(String role);

}
