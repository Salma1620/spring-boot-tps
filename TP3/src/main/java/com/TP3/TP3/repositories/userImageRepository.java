package com.TP3.TP3.repositories;

import com.TP3.TP3.models.User;
import com.TP3.TP3.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userImageRepository extends JpaRepository<UserImage, Long> {
    Optional<UserImage> findUserImageByUser(User user);
}
