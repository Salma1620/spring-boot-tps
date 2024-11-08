package com.TP3.TP3.controllers;

import com.TP3.TP3.models.User;
import com.TP3.TP3.models.UserImage;
import com.TP3.TP3.services.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class userController {
    private userService userService;

    public userController(userService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Créer un nouvel Utilisateur
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Récupérer un Utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Associer un Rôle à un Utilisateur
    @PutMapping("/{utilisateurId}/role/{roleId}")
    public ResponseEntity<User> assignRoleToUser(@PathVariable Long utilisateurId, @PathVariable Long roleId) {
        Optional<User> updatedUser = userService.assignRoleToUser(utilisateurId, roleId);
        return updatedUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Ajouter une image à un Utilisateur
    @PostMapping("/{utilisateurId}/image")
    public ResponseEntity<UserImage> addImageToUser(@PathVariable Long utilisateurId, @RequestBody UserImage image) {
        UserImage addedImage = userService.addImageToUser(utilisateurId, image).getUserImage();
        return new ResponseEntity<>(addedImage, HttpStatus.CREATED);
    }

    // Supprimer un Utilisateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Supprimer un Rôle par ID
    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        boolean isDeleted = userService.deleteRole(roleId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Supprimer une image d'un Utilisateur
    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public ResponseEntity<Void> deleteImageFromUser(@PathVariable Long utilisateurId, @PathVariable Long imageId) {
        boolean isDeleted = userService.deleteImageFromUser(utilisateurId, imageId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
