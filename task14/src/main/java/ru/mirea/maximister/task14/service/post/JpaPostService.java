package ru.mirea.maximister.task14.service.post;

import lombok.extern.slf4j.Slf4j;
import ru.mirea.maximister.task14.domain.Post;
import ru.mirea.maximister.task14.dto.AddPostRequest;
import ru.mirea.maximister.task14.dto.PostResponse;
import ru.mirea.maximister.task14.dto.RemovePostRequest;
import ru.mirea.maximister.task14.repository.post.JpaPostRepository;
import ru.mirea.maximister.task14.service.mappers.PostToPostResponseMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JpaPostService implements PostService {
    private final JpaPostRepository postRepository;

    public JpaPostService(JpaPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostResponse> getPosts() {
        log.info("getting all posts");
        return postRepository.findAll().stream()
                .map(PostToPostResponseMapper::postToPostResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getPost(Long id) {
        log.info("getting post with id {}", id);
        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
        return PostToPostResponseMapper.postToPostResponse(post);
    }

    @Override
    public void addPost(AddPostRequest addPostRequest) {
        Post post = new Post(addPostRequest.text(), OffsetDateTime.now());
        postRepository.save(post);
        log.info("added new post {}", post);
    }

    @Override
    public void deletePost(RemovePostRequest request) {
        postRepository.deleteById(request.id());
        log.info("deleted post {}", request);
    }

    @Override
    public List<PostResponse> getPostsByFilter(String filteredBy, String value) {
        log.info("getting posts by filter {} and value {}", filteredBy, value);
        switch (filteredBy) {
            case "id" -> {
                return postRepository.findPostsByIdGreaterThan(Long.parseLong(value))
                        .stream()
                        .map(PostToPostResponseMapper::postToPostResponse)
                        .collect(Collectors.toList());
            }
            case "text" -> {
                return postRepository.findPostsByTextGreaterThanEqual(value)
                        .stream()
                        .map(PostToPostResponseMapper::postToPostResponse)
                        .collect(Collectors.toList());
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
