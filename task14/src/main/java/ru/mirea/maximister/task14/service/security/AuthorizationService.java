package ru.mirea.maximister.task14.service.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.maximister.task14.model.dto.SecurityUserDto;
import ru.mirea.maximister.task14.model.security.Role;
import ru.mirea.maximister.task14.model.security.SecurityUser;
import ru.mirea.maximister.task14.repository.security.SecurityUserRepository;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final SecurityUserRepository userRepository;
    private final PasswordEncoder encoder;

    public boolean signUp(SecurityUserDto userData) {

        SecurityUser user = new SecurityUser(
                userData.name(),
                encoder.encode(userData.password())
        );
        user.getRoles().add(Role.USER_ROLE);

        if (userRepository.existsByName(user.getUsername())) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public void deleteUser(SecurityUserDto userData) {
        SecurityUser user = userRepository.findByName(userData.name())
                .orElseThrow(() -> new UsernameNotFoundException(""));

        if (!encoder.matches(userData.password(), user.getPassword())) {
            throw new IllegalArgumentException("invalid password for user " + user.getName());
        }

        userRepository.delete(user);
    }
}
