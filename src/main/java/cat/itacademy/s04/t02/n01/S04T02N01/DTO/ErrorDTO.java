package cat.itacademy.s04.t02.n01.S04T02N01.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> errors
) {
    public ErrorDTO(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}
