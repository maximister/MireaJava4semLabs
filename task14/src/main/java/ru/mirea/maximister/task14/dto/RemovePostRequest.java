package ru.mirea.maximister.task14.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record RemovePostRequest(
        @PositiveOrZero
        Long id,
        @NotEmpty
        String text,
        @NotNull
        OffsetDateTime creationDate
) {
}
