package ru.mirea.maximister.task14.repository.post;

import org.springframework.stereotype.Repository;
import ru.mirea.maximister.task14.domain.Post;
import ru.mirea.maximister.task14.dto.AddPostRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("InMemoryPostRepo")
public class InMemoryPostRepo implements PostRepository {
    private List<Post> posts = new ArrayList<>();
    private long ID = 0;

    @Override
    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }

    @Override
    public Post getPost(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addPost(AddPostRequest addPostRequest) {
        posts.add(Post.builder()
                .text(addPostRequest.text())
                .id(++ID)
                .build()
        );
    }

    @Override
    public void deletePost(Long id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
