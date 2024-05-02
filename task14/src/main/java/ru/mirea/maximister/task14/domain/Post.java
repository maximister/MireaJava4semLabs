package ru.mirea.maximister.task14.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "posts")
public class Post {
    public Post(String text, OffsetDateTime creationDate) {
        this.text = text;
        this.creationDate = creationDate;
    }

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private OffsetDateTime creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @PostConstruct
    private void init() {
        creationDate = OffsetDateTime.now();
    }
}
