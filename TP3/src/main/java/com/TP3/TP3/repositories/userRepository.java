package com.TP3.TP3.repositories;

import com.TP3.TP3.models.Role;
import com.TP3.TP3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findUsersByRole(Role role);

}
