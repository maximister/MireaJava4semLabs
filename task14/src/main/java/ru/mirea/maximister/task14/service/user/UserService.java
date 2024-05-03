package ru.mirea.maximister.task14.service.user;

import ru.mirea.maximister.task14.model.dto.AddUserRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse getUser(Long id);
    void addUser(AddUserRequest addUserRequest);
    void deleteUser(RemoveUserRequest request);

    List<PostResponse> getPosts(Long userId);
    void addPostToUser(Long userId, Long postId);

    List<UserResponse> getUsersBysFilter(String filteredBy, String value);
}

