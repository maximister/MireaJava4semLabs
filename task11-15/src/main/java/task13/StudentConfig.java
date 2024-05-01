package task13;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student", ignoreUnknownFields = false)
public record StudentConfig(String name, String lastName, String group) {
}
