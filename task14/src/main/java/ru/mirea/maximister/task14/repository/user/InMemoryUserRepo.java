package ru.mirea.maximister.task14.repository.user;

import ru.mirea.maximister.task14.model.domain.User;
import ru.mirea.maximister.task14.model.dto.AddUserRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryUserRepo implements UserRepository {
    private List<User> users = new ArrayList<>();
    private long ID = 0;

    @Override
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public User getUser(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        users.add(
                User.builder()
                        .firstName(addUserRequest.firstName())
                        .lastName(addUserRequest.lastName())
                        .middleName(addUserRequest.middleName())
                        .birthDate(addUserRequest.birthDate())
                        .id(++ID)
                        .build()
        );
    }

    @Override
    public void deleteUser(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
