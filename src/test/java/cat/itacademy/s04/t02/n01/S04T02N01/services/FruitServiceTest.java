package cat.itacademy.s04.t02.n01.S04T02N01.services;

import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.repository.FruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class FruitServiceTest {

    @Autowired
    private FruitService fruitService;

    @Autowired
    private FruitRepository fruitRepository;

    private Fruit fruit;

    @BeforeEach
    void setup() {
        fruit = new Fruit();
        fruit.setName("Mango");
        fruit.setWeightKilos(BigDecimal.valueOf(1.2));
        fruit = fruitRepository.save(fruit);
    }

    @Test
    void testGetAllFruits() {
        List<Fruit> fruits = fruitService.getAllFruits();
        assertThat(fruits).isNotEmpty();
        assertThat(fruits.get(0).getName()).isEqualTo("Mango");
    }

    @Test
    void testGetFruitById() {
        Fruit found = fruitService.getFruitById(fruit.getId());
        assertThat(found.getName()).isEqualTo("Mango");
    }

    @Test
    void testCreateFruit() {
        Fruit newFruit = new Fruit();
        newFruit.setName("Peach");
        newFruit.setWeightKilos(BigDecimal.valueOf(0.75));

        Fruit saved = fruitService.createFruit(newFruit);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Peach");
    }

    @Test
    void testUpdateFruit() {
        fruit.setName("Updated Mango");
        Fruit updated = fruitService.updateFruit(fruit.getId(), fruit);
        assertThat(updated.getName()).isEqualTo("Updated Mango");
    }

    @Test
    void testDeleteFruit() {
        fruitService.deleteFruit(fruit.getId());
        assertThat(fruitRepository.findById(fruit.getId())).isEmpty();
    }
}
