package ru.mirea.maximister.task14.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AddUserRequest(
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
