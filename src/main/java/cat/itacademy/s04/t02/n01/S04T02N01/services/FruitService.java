package cat.itacademy.s04.t02.n01.S04T02N01.services;

import cat.itacademy.s04.t02.n01.S04T02N01.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    @Autowired
    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository repository) {
        this.fruitRepository = repository;
    }

    public Fruit createFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    public Fruit getFruitById(Long id) {
        return fruitRepository.findById(id).orElseThrow(() -> new FruitNotFoundException(id));
    }

    public Fruit updateFruit(long id, Fruit updatedFruit) {
        Fruit existingFruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id));

        existingFruit.setName(updatedFruit.getName());
        existingFruit.setWeightKilos(updatedFruit.getWeightKilos());

        return fruitRepository.save(existingFruit);
    }


    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }
}


