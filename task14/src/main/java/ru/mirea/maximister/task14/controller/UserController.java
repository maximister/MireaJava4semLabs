package ru.mirea.maximister.task14.controller;

import org.springframework.web.bind.annotation.*;
import ru.mirea.maximister.task14.model.dto.AddUserRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.model.dto.UserResponse;
import ru.mirea.maximister.task14.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody AddUserRequest request) {
        userService.addUser(request);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody RemoveUserRequest request) {
        userService.deleteUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/posts/{id}")
    public List<PostResponse> getPosts(@PathVariable Long id) {
        return userService.getPosts(id);
    }

    @PostMapping("/{userId}/posts/{postId}")
    public void addPost(@PathVariable Long userId, @PathVariable Long postId) {
        userService.addPostToUser(userId, postId);
    }

    @GetMapping("/filtered")
    public List<UserResponse> getUsersByFilter(@RequestParam String filteredBy, @RequestParam String value) {
        return userService.getUsersBysFilter(filteredBy, value);
    }
}
