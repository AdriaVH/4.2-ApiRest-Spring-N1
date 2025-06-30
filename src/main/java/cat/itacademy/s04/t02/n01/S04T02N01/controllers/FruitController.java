package cat.itacademy.s04.t02.n01.S04T02N01.controllers;

import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fruits")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    public FruitController (FruitService service) {
        this.fruitService = service;
    }

    @PostMapping
    public Fruit createFruit(@RequestBody Fruit fruit) {
        return fruitService.createFruit(fruit);
    }

    @GetMapping
    public List<Fruit> getAllFruits() {
        return fruitService.getAllFruits();
    }

    @GetMapping("/{id}")
    public Fruit getFruitById(@PathVariable Long id) {
        return fruitService.getFruitById(id);
    }

    @PutMapping("/{id}")
    public Fruit updateFruit(@PathVariable int id, @RequestBody Fruit fruit) {
        return fruitService.updateFruit(id, fruit);
    }

    @DeleteMapping("/{id}")
    public void deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
    }
}
