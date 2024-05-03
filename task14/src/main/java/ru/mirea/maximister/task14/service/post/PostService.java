package ru.mirea.maximister.task14.service.post;

import ru.mirea.maximister.task14.model.dto.AddPostRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemovePostRequest;

import java.util.List;

public interface PostService {
    List<PostResponse> getPosts();
    PostResponse getPost(Long id);
    void addPost(AddPostRequest addPostRequest);
    void deletePost(RemovePostRequest request);

    List<PostResponse> getPostsByFilter(String filteredBy, String value);
}

