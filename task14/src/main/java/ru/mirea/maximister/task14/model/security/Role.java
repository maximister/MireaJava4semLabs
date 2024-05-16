package ru.mirea.maximister.task14.model.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER_ROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
