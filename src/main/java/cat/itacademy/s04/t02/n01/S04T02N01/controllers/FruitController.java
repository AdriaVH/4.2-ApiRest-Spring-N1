package cat.itacademy.s04.t02.n01.S04T02N01.controllers;

import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitDTO;
import cat.itacademy.s04.t02.n01.S04T02N01.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

    private FruitService fruitService;

    @Autowired
    public FruitController (FruitService service) {
        this.fruitService = service;
    }
    @PostMapping("/add")
    public ResponseEntity<Fruit> createFruit(@Valid @RequestBody FruitDTO fruitDTO) {
        Fruit fruit = fruitService.createFruit(convertToEntity(fruitDTO));
        return ResponseEntity.ok(fruit);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return ResponseEntity.ok(fruitService.getAllFruits());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        return ResponseEntity.ok(fruit);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fruit> updateFruit(
            @PathVariable Long id,
            @Valid @RequestBody FruitDTO fruitDTO) {

        Fruit fruit = fruitService.updateFruit(id, convertToEntity(fruitDTO));
        return ResponseEntity.ok(fruit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }

    private Fruit convertToEntity(FruitDTO dto) {
        Fruit fruit = new Fruit();
        fruit.setName(dto.getName());
        fruit.setWeightKilos(dto.getWeightKilos());
        return fruit;
    }
}