package ru.mirea.maximister.task14.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.maximister.task14.domain.Post;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByIdGreaterThan(Long id);
    List<Post> findPostsByTextGreaterThanEqual(String text);
}
