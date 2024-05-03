package ru.mirea.maximister.task14.service.mappers;

import lombok.experimental.UtilityClass;
import ru.mirea.maximister.task14.model.domain.User;
import ru.mirea.maximister.task14.model.dto.UserResponse;

@UtilityClass
public class UserToUserResponseMapper {
    public UserResponse userToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getBirthDate()
        );
    }
}
