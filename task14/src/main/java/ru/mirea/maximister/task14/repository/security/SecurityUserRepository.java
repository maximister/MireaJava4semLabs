package ru.mirea.maximister.task14.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.maximister.task14.model.security.SecurityUser;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByName(String name);
    boolean existsByName(String name);
}
