package com.TP3.TP3.services;

import com.TP3.TP3.models.Role;
import com.TP3.TP3.models.User;
import com.TP3.TP3.models.UserImage;
import com.TP3.TP3.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class userService {
    private final userRepository userRepository;
    private final userImageRepository userImageRepository;
    private final roleRepository roleRepository;

    public userService(userRepository userRepository, roleRepository roleRepository, userImageRepository userImageRepository, com.TP3.TP3.repositories.userImageRepository userImageRepository1, com.TP3.TP3.repositories.roleRepository roleRepository1) {
        this.userRepository = userRepository;
        this.userImageRepository = userImageRepository1;
        this.roleRepository = roleRepository1;
    }

    //@Transactional
    public User createUserWithRole(User user, String roleName) {
        Optional<Role> role = roleRepository.findRoleByRoleName(roleName);
        if (role.isPresent()) {
            user.setRole(role.get());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
    }

    @Transactional
    public User addImageToUser(Long userId, UserImage userImage) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            userImage.setUser(user); // Associe l'image à l'utilisateur
            userImageRepository.save(userImage);
            user.setUserImage(userImage); // Mettez à jour la référence de l'image dans l'utilisateur
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    //@Transactional(readOnly = true)
    public Optional<List<User>> getUsersByRoleName(String roleName) {
        Optional<Role> role = roleRepository.findRoleByRoleName(roleName);
        if (role.isPresent()) {
            return Optional.ofNullable(userRepository.findUsersByRole(role.get()));
        } else {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> assignRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (user.isPresent() && role.isPresent()) {
            user.get().setRole(role.get());
            return Optional.of(userRepository.save(user.get()));
        }

        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    public boolean deleteRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()) {
            roleRepository.delete(role.get());
            return true;
        }
        return false;
    }

    public boolean deleteImageFromUser(Long userId, Long imageId) {
        Optional<UserImage> image = userImageRepository.findById(imageId);
        if (image.isPresent() && image.get().getUser().getId().equals(userId)) {
            userImageRepository.delete(image.get());
            return true;
        }
        return false;
    }




}
