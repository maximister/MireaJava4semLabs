package ru.mirea.maximister.task14.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mirea.maximister.task14.model.dto.SecurityUserDto;
import ru.mirea.maximister.task14.model.security.SecurityUser;
import ru.mirea.maximister.task14.repository.security.SecurityUserRepository;
import ru.mirea.maximister.task14.service.security.AuthorizationService;

import java.util.Optional;

public class AuthorizationServiceTest {
    @Test
    @DisplayName("testing  signUp method")
    public void signUp_shouldWorkCorrectly() {
        var userRepository = Mockito.mock(SecurityUserRepository.class);
        PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
        var authService = new AuthorizationService(userRepository, encoder);

        Mockito.when(userRepository.existsByName("existed")).thenReturn(true);
        Mockito.when(userRepository.existsByName("nonExisted")).thenReturn(false);

        Assertions.assertTrue(authService.signUp(new SecurityUserDto(
                "nonExisted", "password"
        )));
        Assertions.assertFalse(authService.signUp(new SecurityUserDto(
                "existed", "password"
        )));
    }

    @Test
    @DisplayName("testing  delete method")
    public void deleteUser_shouldWorkCorrectly() {
        var userRepository = Mockito.mock(SecurityUserRepository.class);
        PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
        var authService = new AuthorizationService(userRepository, encoder);

        Mockito.when(userRepository.findByName("existed")).thenReturn(
                Optional.of(new SecurityUser("User", "Password"))
        );
        Mockito.when(userRepository.findByName("nonExisted"))
                .thenReturn(Optional.empty());

        Mockito.when(encoder.matches("correct", "Password"))
                .thenReturn(true);
        Mockito.when(encoder.matches("invalid", "Password"))
                .thenReturn(false);


        //correct data
        Assertions.assertDoesNotThrow(() -> authService.deleteUser(new SecurityUserDto(
                "existed",
                "correct"
        )));
        //invalid user
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> authService.deleteUser(new SecurityUserDto(
                "nonExisted",
                "correct"
        )));
        //invalid password
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> authService.deleteUser(new SecurityUserDto(
                        "existed",
                        "invalid"
                )));
    }
}
