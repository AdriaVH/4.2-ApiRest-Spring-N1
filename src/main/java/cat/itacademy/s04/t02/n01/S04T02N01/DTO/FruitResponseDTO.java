package cat.itacademy.s04.t02.n01.S04T02N01.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor @Getter
@Setter
public class FruitResponseDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name can be max 50 characters")
    private String name;

    @NotNull(message = "Weight must be provided")
    @DecimalMin(value = "0.01", message = "Weight must be greater than 0")
    private BigDecimal weightKilos;
}

