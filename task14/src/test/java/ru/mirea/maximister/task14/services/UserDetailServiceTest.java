package ru.mirea.maximister.task14.services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mirea.maximister.task14.model.security.SecurityUser;
import ru.mirea.maximister.task14.repository.security.SecurityUserRepository;
import ru.mirea.maximister.task14.service.security.DefaultUserDetailService;

import java.util.Optional;

public class UserDetailServiceTest {
    @Test
    @DisplayName("testing  loadUserByUsername method")
    public void loadUserByUsername_ShouldReturnCorrectUser() {
        var userRepository = Mockito.mock(SecurityUserRepository.class);
        var userService = new DefaultUserDetailService(userRepository);

        Mockito.when(userRepository.findByName("user"))
                .thenReturn(Optional.of(
                                new SecurityUser(
                                        "user",
                                        "password"
                                )
                        )
                );

        UserDetails user = userService.loadUserByUsername("user");
        Assertions.assertThat(user.getUsername()).isEqualTo("user");
    }
}
