package cat.itacademy.s04.t02.n01.S04T02N01.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter@Setter
public class FruitRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name can be max 50 characters")
    private String name;

    @NotNull(message = "Weight must be provided")
    @DecimalMin(value = "0.01", message = "Weight must be greater than 0")
    private BigDecimal weightKilos;

}
