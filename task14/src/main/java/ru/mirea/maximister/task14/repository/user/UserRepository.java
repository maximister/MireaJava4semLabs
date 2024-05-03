package ru.mirea.maximister.task14.repository.user;

import ru.mirea.maximister.task14.model.domain.User;
import ru.mirea.maximister.task14.model.dto.AddUserRequest;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    User getUser(Long id);
    void addUser(AddUserRequest addUserRequest);
    void deleteUser(Long id);
}
