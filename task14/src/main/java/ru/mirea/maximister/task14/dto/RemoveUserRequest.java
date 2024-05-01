package ru.mirea.maximister.task14.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

public record RemoveUserRequest(
        @PositiveOrZero
        Long id,
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        @NotEmpty
        String middleName,
        @NotNull
        Date birthDate
) {
}
