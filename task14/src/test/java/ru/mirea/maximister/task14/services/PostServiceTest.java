package ru.mirea.maximister.task14.services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mirea.maximister.task14.model.domain.Post;
import ru.mirea.maximister.task14.model.dto.AddPostRequest;
import ru.mirea.maximister.task14.model.dto.RemovePostRequest;
import ru.mirea.maximister.task14.repository.post.JpaPostRepository;
import ru.mirea.maximister.task14.service.mappers.PostToPostResponseMapper;
import ru.mirea.maximister.task14.service.post.JpaPostService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class PostServiceTest {
    private static JpaPostRepository postRepository;
    private static List<Post> posts;
    private static OffsetDateTime now = OffsetDateTime.now();

    @BeforeAll
    public static void init() {
        postRepository = Mockito.mock(JpaPostRepository.class);
        posts = List.of(
                new Post(1L, "post1", now.minusDays(1), null),
                new Post(2L, "post2", now.minusDays(2), null),
                new Post(3L, "post3", now.minusDays(3), null)
        );
        Mockito.when(postRepository.findAll()).thenReturn(posts);
    }

    @Test
    @DisplayName("Тестирование getPosts")
    public void getPosts_ShouldReturnAllPosts() {
        var service = new JpaPostService(
                postRepository
        );

        Assertions.assertThat(
                service.getPosts()
        ).containsAll(posts.stream().map(PostToPostResponseMapper::postToPostResponse).toList());
    }

    @Test
    @DisplayName("Тестирование getPost")
    public void getPost_ShouldReturnCorrectPost() {
        var service = new JpaPostService(
                postRepository
        );

        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(posts.get(0)));

        Assertions.assertThat(
                service.getPost(1L).text()).isEqualTo("post1");
    }

    @Test
    @DisplayName("Тестирование addPost")
    public void addPost_ShouldWorkCorrectly() {
        var service = new JpaPostService(
                postRepository
        );

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                service.addPost(new AddPostRequest("new post"))
        );
    }

    @Test
    @DisplayName("Тестирование deletePost")
    public void deletePost_ShouldRemovePostCorrectly() {
        var service = new JpaPostService(
                postRepository
        );

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                service.deletePost(new RemovePostRequest(1L, "post1", now.minusDays(1)))
        );
    }
}
