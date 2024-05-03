package ru.mirea.maximister.task14.service.mappers;

import lombok.experimental.UtilityClass;
import ru.mirea.maximister.task14.model.domain.Post;
import ru.mirea.maximister.task14.model.dto.PostResponse;

@UtilityClass
public class PostToPostResponseMapper {
    public PostResponse postToPostResponse(Post post) {
        return new PostResponse(post.getId(), post.getText(), post.getCreationDate());
    }
}
