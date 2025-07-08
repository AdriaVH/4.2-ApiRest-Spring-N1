package cat.itacademy.s04.t02.n01.S04T02N01.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "fruit")
@Getter@Setter @NoArgsConstructor @AllArgsConstructor
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private BigDecimal weightKilos;


}
