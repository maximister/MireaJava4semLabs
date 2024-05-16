package ru.mirea.maximister.task14.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.maximister.task14.model.security.SecurityUser;
import ru.mirea.maximister.task14.repository.security.SecurityUserRepository;

import java.util.Optional;

@Service
public class DefaultUserDetailService implements UserDetailsService {
    private final SecurityUserRepository userRepository;

    public DefaultUserDetailService(SecurityUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityUser> user = userRepository.findByName(username);
        return user.orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
