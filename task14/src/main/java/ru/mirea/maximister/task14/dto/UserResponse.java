package ru.mirea.maximister.task14.dto;

import java.util.Date;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String middleName,
        Date birthDate
) {
}
