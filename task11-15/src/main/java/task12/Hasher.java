package task12;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class Hasher {
    @SneakyThrows
    public String getHash(byte[] data) {
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(data);
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            String s = Integer.toHexString(0xff & b);
            s = (s.length() == 1) ? "0" + s : s;
            sb.append(s);
        }
        return sb.toString();
    }
}