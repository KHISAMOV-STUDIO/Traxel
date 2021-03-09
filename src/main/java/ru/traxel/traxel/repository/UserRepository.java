package ru.traxel.traxel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.traxel.traxel.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}