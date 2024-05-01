package task12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileWriter {
    private final Hasher hasher;
    private final List<String> args;

    @PostConstruct
    @SneakyThrows
    private void init() {
        Path path = Path.of(args.get(0));
        Path hashPath = Path.of(args.get(1));
        Files.deleteIfExists(hashPath);
        Files.createFile(hashPath);
        if (!Files.exists(path)) {
            Files.write(hashPath, "null".getBytes());
        }
        String text = hasher.getHash(Files.readAllBytes(path));
        Files.write(hashPath, text.getBytes());
    }

    @PreDestroy
    @SneakyThrows
    private void destroy() {
        Path path = Path.of(args.get(0));
        Files.deleteIfExists(path);
    }
}