package cat.itacademy.s04.t02.n01.S04T02N01.DTO;

import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import org.springframework.stereotype.Component;

@Component
public class FruitMapper {

    public FruitResponseDTO toDTO(Fruit fruit) {
        if (fruit == null) return null;
        return new FruitResponseDTO(fruit.getName(), fruit.getWeightKilos());
    }

    public Fruit toEntity(FruitRequestDTO dto) {
        if (dto == null) return null;
        Fruit fruit = new Fruit();
        fruit.setName(dto.getName());
        fruit.setWeightKilos(dto.getWeightKilos());
        return fruit;
    }
//
//    public void updateEntityFromDTO(FruitRequestDTO dto, Fruit fruit) {
//        if (dto == null || fruit == null) return;
//        fruit.setName(dto.getName());
//        fruit.setWeightKilos(dto.getWeightKilos());
//    }
}
