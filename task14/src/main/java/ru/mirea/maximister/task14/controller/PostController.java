package ru.mirea.maximister.task14.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.maximister.task14.dto.AddPostRequest;
import ru.mirea.maximister.task14.dto.PostResponse;
import ru.mirea.maximister.task14.dto.RemovePostRequest;
import ru.mirea.maximister.task14.service.post.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public void addPost(@RequestBody AddPostRequest request) {
        postService.addPost(request);
    }

    @DeleteMapping
    public void deletePost(@RequestBody RemovePostRequest request) {
        postService.deletePost(request);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}
