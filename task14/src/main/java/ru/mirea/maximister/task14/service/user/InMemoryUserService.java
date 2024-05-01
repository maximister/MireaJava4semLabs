package ru.mirea.maximister.task14.service.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mirea.maximister.task14.dto.AddUserRequest;
import ru.mirea.maximister.task14.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.dto.UserResponse;
import ru.mirea.maximister.task14.repository.user.UserRepository;
import ru.mirea.maximister.task14.service.mappers.UserToUserResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service("InMemoryUserService")
public class InMemoryUserService implements UserService {
    private final UserRepository userRepository;

    public InMemoryUserService(@Value("InMemoryUserRepo") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.getUsers().stream()
                .map(UserToUserResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long id) {
        return UserToUserResponseMapper.userToUserResponse(userRepository.getUser(id));
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        userRepository.addUser(addUserRequest);
    }

    @Override
    public void deleteUser(RemoveUserRequest request) {
        userRepository.deleteUser(request.id());
    }
}
