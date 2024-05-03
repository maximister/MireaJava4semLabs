package ru.mirea.maximister.task14.model.dto;

import java.time.OffsetDateTime;

public record PostResponse(
        Long id,
        String text,
        OffsetDateTime creationDate
) {
}
