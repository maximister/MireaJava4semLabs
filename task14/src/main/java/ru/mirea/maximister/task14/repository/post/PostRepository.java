package ru.mirea.maximister.task14.repository.post;

import ru.mirea.maximister.task14.model.domain.Post;
import ru.mirea.maximister.task14.model.dto.AddPostRequest;

import java.util.List;

public interface PostRepository {
    List<Post> getPosts();
    Post getPost(Long id);
    void addPost(AddPostRequest addPostRequest);
    void deletePost(Long id);
}
