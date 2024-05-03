package ru.mirea.maximister.task14.service.post;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.mirea.maximister.task14.model.dto.AddPostRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemovePostRequest;
import ru.mirea.maximister.task14.repository.post.PostRepository;
import ru.mirea.maximister.task14.service.mappers.PostToPostResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPostService implements PostService {
    private final PostRepository postRepository;

    public InMemoryPostService(@Qualifier("InMemoryPostRepo") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostResponse> getPosts() {
        return postRepository.getPosts().stream()
                .map(PostToPostResponseMapper::postToPostResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getPost(Long id) {
        return PostToPostResponseMapper.postToPostResponse(postRepository.getPost(id));
    }

    @Override
    public void addPost(AddPostRequest addPostRequest) {
        postRepository.addPost(addPostRequest);
    }

    @Override
    public void deletePost(RemovePostRequest request) {
        postRepository.deletePost(request.id());
    }

    @Override
    public List<PostResponse> getPostsByFilter(String filteredBy, String value) {
        return null;
    }
}
