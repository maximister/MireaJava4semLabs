package ru.mirea.maximister.task14.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.maximister.task14.model.domain.Post;
import ru.mirea.maximister.task14.model.domain.User;
import ru.mirea.maximister.task14.model.dto.AddUserRequest;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.model.dto.UserResponse;
import ru.mirea.maximister.task14.repository.post.JpaPostRepository;
import ru.mirea.maximister.task14.repository.user.JpaUserRepository;
import ru.mirea.maximister.task14.service.mappers.PostToPostResponseMapper;
import ru.mirea.maximister.task14.service.mappers.UserToUserResponseMapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Transactional
public class JpaUserService implements UserService {
    private final JpaUserRepository userRepository;
    private final JpaPostRepository postRepository;

    public JpaUserService(JpaUserRepository userRepository, JpaPostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info("getting all users");
        return userRepository.findAll().stream()
                .map(UserToUserResponseMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long id) {
        log.info("getting user with id {}", id);
        return UserToUserResponseMapper.userToUserResponse(userRepository.findById(id).orElseThrow());
    }

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        User user = new User(
                addUserRequest.firstName(),
                addUserRequest.lastName(),
                addUserRequest.middleName(),
                addUserRequest.birthDate());
        userRepository.save(user);
        log.info("added new user {}", user);
    }

    @Override
    public void deleteUser(RemoveUserRequest request) {
        userRepository.deleteById(request.id());
        log.info("deleted user {}", request);
    }

    @Override
    public List<PostResponse> getPosts(Long userId) {
        log.info("getting posts from user {}", userId);
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        return user.getPosts().stream().map(PostToPostResponseMapper::postToPostResponse).toList();
    }

    @Override
    public void addPostToUser(Long userId, Long postId) {
        log.info("adding post {} to user {}", postId, userId);
        User user = userRepository.findById(postId).orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        user.getPosts().add(post);
        post.setUser(user);
    }

    @Override
    public List<UserResponse> getUsersBysFilter(String filteredBy, String value) {
        log.info("getting users by filter {} and value {}", filteredBy, value);
        switch (filteredBy) {
            case "firstName" -> {
                return userRepository.findUsersByFirstName(value)
                        .stream()
                        .map(UserToUserResponseMapper::userToUserResponse)
                        .collect(Collectors.toList());
            }
            case "lastName" -> {
                return userRepository.findUsersByLastName(value)
                        .stream()
                        .map(UserToUserResponseMapper::userToUserResponse)
                        .collect(Collectors.toList());
            }
            case "middleName" -> {
                return userRepository.findUsersByMiddleName(value)
                        .stream()
                        .map(UserToUserResponseMapper::userToUserResponse)
                        .collect(Collectors.toList());
            }
            case "birthDate" -> {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = null;
                try {
                    date = format.parse(value);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return userRepository.findUserByBirthDateAfter(date)
                        .stream()
                        .map(UserToUserResponseMapper::userToUserResponse)
                        .collect(Collectors.toList());
            }

            default -> throw new IllegalArgumentException();
        }
    }
}
