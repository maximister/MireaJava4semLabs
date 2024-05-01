package ru.mirea.maximister.task14.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.maximister.task14.dto.AddUserRequest;
import ru.mirea.maximister.task14.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.dto.UserResponse;
import ru.mirea.maximister.task14.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

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

}
