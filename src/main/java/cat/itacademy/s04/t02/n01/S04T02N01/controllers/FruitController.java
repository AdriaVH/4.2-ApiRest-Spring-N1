package cat.itacademy.s04.t02.n01.S04T02N01.controllers;

import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitMapper;
import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.S04T02N01.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

    @Autowired
    private FruitMapper fruitMapper;

    private final FruitService fruitService;

    public FruitController (FruitService service) {
        this.fruitService = service;
    }
    @PostMapping
    public ResponseEntity<FruitResponseDTO> createFruit(@Valid @RequestBody FruitRequestDTO fruitRequestDTO) {
        Fruit fruit = fruitMapper.toEntity(fruitRequestDTO);
        Fruit saved = fruitService.createFruit(fruit);
        return ResponseEntity.ok(fruitMapper.toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<Fruit>> getAllFruits() {
        return ResponseEntity.ok(fruitService.getAllFruits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        return ResponseEntity.ok(fruit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fruit> updateFruit(
            @PathVariable Long id,
            @Valid @RequestBody FruitRequestDTO fruitRequestDTO) {

        Fruit fruit = fruitService.updateFruit(id, convertToEntity(fruitRequestDTO));
        return ResponseEntity.ok(fruitToDTO(fruit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }

    private FruitResponseDTO fruitToDTO (Fruit fruit) {

        return new FruitResponseDTO(fruit.getId(),fruit.getName(),fruit.getWeightKilos());
    }
}