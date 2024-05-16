package ru.mirea.maximister.task14.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mirea.maximister.task14.model.dto.PostResponse;
import ru.mirea.maximister.task14.model.dto.UserResponse;
import ru.mirea.maximister.task14.service.post.PostService;
import ru.mirea.maximister.task14.service.user.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Service
@ManagedResource
@RequiredArgsConstructor
@Log4j2
@EnableScheduling
public class SaveEntitiesService {
    private final UserService userService;
    private final PostService postService;
    private final ObjectMapper objectMapper;

    @ManagedOperation
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    @SneakyThrows
    public void save() {
        log.info("Start saving entities");
        Path pathToDir = Path.of("task14/src/main/resources/entitiesStorage");
        File dir = new File(pathToDir.toString());
        if (dir.exists()) {
            FileUtils.deleteDirectory(new File(pathToDir.toString()));
        }

        Files.createDirectory(pathToDir);

        Path usersPath = pathToDir.resolve("users");
        Path postsPath = pathToDir.resolve("posts");
        Files.createDirectory(usersPath);
        Files.createDirectory(postsPath);
        saveAllEntities(usersPath, postsPath);
    }

    private void saveAllEntities(Path userPath, Path postPath) {
        for (UserResponse user: userService.getUsers()) {
            try {
                Files.write(
                        userPath.resolve(user.id() + ".json"),
                        objectMapper.writeValueAsBytes(user)
                );
            } catch (IOException e) {
                log.warn("error e {} during saving user entity {}", e, user);
                throw new RuntimeException(e);
            }
        }

        for (PostResponse post: postService.getPosts()) {
            try {
                Files.write(
                        postPath.resolve(post.id() + ".json"),
                        objectMapper.writeValueAsBytes(post)
                );
            } catch (IOException e) {
                log.warn("error e {} during saving post entity {}", e, post);
                throw new RuntimeException(e);
            }
        }

        log.info("All entities were successfully saved");
    }
}