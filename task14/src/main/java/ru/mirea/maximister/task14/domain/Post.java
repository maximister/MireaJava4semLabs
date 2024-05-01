package ru.mirea.maximister.task14.domain;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    private Long id;
    private String text;
    private OffsetDateTime creationDate;

    @PostConstruct
    private void init() {
        creationDate = OffsetDateTime.now();
    }
}
