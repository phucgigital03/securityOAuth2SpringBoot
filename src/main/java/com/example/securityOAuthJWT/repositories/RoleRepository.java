package com.example.securityOAuthJWT.repositories;

import com.example.securityOAuthJWT.model.AppRole;
import com.example.securityOAuthJWT.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}
