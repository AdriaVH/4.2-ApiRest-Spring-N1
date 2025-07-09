package cat.itacademy.s04.t02.n01.S04T02N01.exception;

import cat.itacademy.s04.t02.n01.S04T02N01.DTO.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Validation failed for one or more fields.",
                request.getRequestURI(),
                errorDetails
        );

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(FruitNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleFruitNotFoundException(
            FruitNotFoundException ex, HttpServletRequest request) {

        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleMalformedJson(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        Throwable rootCause = ex.getMostSpecificCause();
        String message;

        if (rootCause instanceof InvalidFormatException ife) {
            String field = ife.getPath().getFirst().getFieldName();
            String value = ife.getValue().toString();
            message = String.format("Invalid format for field '%s': '%s'", field, value);
        } else {
            message = "Malformed JSON request: " + rootCause.getMessage();
        }

        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Malformed JSON",
                message,
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
