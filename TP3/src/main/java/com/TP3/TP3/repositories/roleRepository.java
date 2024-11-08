package com.TP3.TP3.repositories;

import com.TP3.TP3.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface roleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByRoleName(String roleName);
}
