package ru.mirea.maximister.task14.service.user;

import ru.mirea.maximister.task14.domain.Post;
import ru.mirea.maximister.task14.dto.AddUserRequest;
import ru.mirea.maximister.task14.dto.PostResponse;
import ru.mirea.maximister.task14.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.dto.UserResponse;

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

