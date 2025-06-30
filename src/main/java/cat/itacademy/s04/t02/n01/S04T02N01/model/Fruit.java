package cat.itacademy.s04.t02.n01.S04T02N01.model;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "fruit")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private BigDecimal weightKilos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeightKilos() {
        return weightKilos;
    }

    public void setWeightKilos(BigDecimal weightKilos) {
        this.weightKilos = weightKilos;
    }


}
