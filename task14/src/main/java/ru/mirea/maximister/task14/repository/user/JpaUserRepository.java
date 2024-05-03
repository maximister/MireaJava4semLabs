package ru.mirea.maximister.task14.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.maximister.task14.model.domain.User;

import java.util.Date;
import java.util.List;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByFirstName(String firstName);
    List<User> findUsersByMiddleName(String middleName);
    List<User> findUsersByLastName(String lastName);
    List<User> findUserByBirthDateAfter(Date date);
}
