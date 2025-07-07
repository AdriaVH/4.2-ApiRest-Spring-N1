package cat.itacademy.s04.t02.n01.S04T02N01.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(FruitNotFoundException.class)
    public ResponseEntity<String> handleFruitNotFoundException(FruitNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMalformedJson(HttpMessageNotReadableException ex) {
        Throwable rootCause = ex.getMostSpecificCause();

        if (rootCause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
            String field = ife.getPath().getFirst().getFieldName();
            String value = ife.getValue().toString();
            String message = String.format("Invalid format for field '%s': '%s'", field, value);
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Malformed JSON request: " + rootCause.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error: " + ex.getMessage());
    }
}
