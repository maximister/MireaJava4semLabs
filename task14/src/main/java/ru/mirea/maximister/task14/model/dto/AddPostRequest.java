package ru.mirea.maximister.task14.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record AddPostRequest(
        @NotEmpty
        String text
) {
}
