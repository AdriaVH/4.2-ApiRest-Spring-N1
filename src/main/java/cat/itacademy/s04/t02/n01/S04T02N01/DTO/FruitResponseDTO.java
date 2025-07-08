package cat.itacademy.s04.t02.n01.S04T02N01.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor @Setter
public class FruitResponseDTO {
    private String name;
    private BigDecimal weightKilos;

}
