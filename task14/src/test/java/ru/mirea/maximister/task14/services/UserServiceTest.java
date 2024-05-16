package ru.mirea.maximister.task14.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mirea.maximister.task14.model.domain.User;
import ru.mirea.maximister.task14.model.dto.AddUserRequest;
import ru.mirea.maximister.task14.model.dto.RemoveUserRequest;
import ru.mirea.maximister.task14.repository.post.JpaPostRepository;
import ru.mirea.maximister.task14.repository.user.JpaUserRepository;
import ru.mirea.maximister.task14.service.mappers.UserToUserResponseMapper;
import ru.mirea.maximister.task14.service.user.JpaUserService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    private static JpaUserRepository userRepository;
    private static JpaPostRepository postRepository;
    private static List<User> entities;

    @BeforeAll
    public static void init() {
        userRepository = Mockito.mock(JpaUserRepository.class);
        postRepository = Mockito.mock(JpaPostRepository.class);
        entities = List.of(
                User.builder().id(1L).firstName("fn1").middleName("mn1").lastName("ln1")
                        .birthDate(Date.from(Instant.now())).posts(List.of()).build(),
                User.builder().id(2L).firstName("fn2").middleName("mn2").lastName("ln2")
                        .birthDate(Date.from(Instant.now())).posts(List.of()).build(),
                User.builder().id(3L).firstName("fn3").middleName("mn3").lastName("ln3")
                        .birthDate(Date.from(Instant.now())).posts(List.of()).build()
        );
        Mockito.when(userRepository.findAll()).thenReturn(entities);
    }

    @Test
    @DisplayName("Тестирование getUsers")
    public void getUsers_ShouldReturnAllUsers() {
        JpaUserService service = new JpaUserService(
                userRepository,
                postRepository
        );
        Assertions.assertThat(
                service.getUsers()
        ).containsAll(entities.stream().map(UserToUserResponseMapper::userToUserResponse).toList());
    }

    @Test
    @DisplayName("Тестирование getUser")
    public void getUser_ShouldReturnAddress() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(entities.get(0)));
        JpaUserService service = new JpaUserService(
                userRepository,
                postRepository
        );

        Assertions.assertThat(
                        service.getUser(1L).firstName())
                .isEqualTo("fn1");
    }

    @Test
    @DisplayName("Тестирование addUser")
    public void addUser_ShouldAddUser() {
        JpaUserService service = new JpaUserService(
                userRepository,
                postRepository
        );

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                service.addUser(new AddUserRequest("", "", "", null))
        );
    }

    @Test
    @DisplayName("Тестирование deleteUser")
    public void deleteUser_ShouldRemoveUser() {
        JpaUserService service = new JpaUserService(
                userRepository,
                postRepository
        );

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                service.deleteUser(new RemoveUserRequest(1L,"", "", "", null))
        );
    }

    @Test
    @DisplayName("Тестирование getUsersByFilter")
    public void getUsersByFilter_ShouldReturnFilteredUsers() {
        JpaUserService service = new JpaUserService(
                userRepository,
                postRepository
        );

        List<User> expected = List.of(
                User.builder().id(1L).firstName("fn1").middleName("mn1").lastName("ln1")
                        .birthDate(Date.from(Instant.now())).posts(List.of()).build()
        );

        Mockito.when(userRepository.findUsersByFirstName("fn1"))
                .thenReturn(expected);

        Assertions.assertThat(
                service.getUsersByFilter("firstName", "fn1")
        ).containsAll(expected.stream().map(UserToUserResponseMapper::userToUserResponse).toList());
    }
}