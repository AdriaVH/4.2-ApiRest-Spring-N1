package cat.itacademy.s04.t02.n01.S04T02N01.services;

import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitMapper;
import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.S04T02N01.DTO.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.S04T02N01.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.S04T02N01.model.Fruit;
import cat.itacademy.s04.t02.n01.S04T02N01.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService {

    @Autowired
    private final FruitRepository fruitRepository;
@Autowired
    private FruitMapper fruitMapper;

    public FruitService(FruitRepository repository) {
        this.fruitRepository = repository;
    }

    public FruitResponseDTO createFruit(FruitRequestDTO requestDTO) {
        Fruit fruit = fruitMapper.toEntity(requestDTO);
        System.out.println(fruit);
        Fruit savedFruit = fruitRepository.save(fruit);
        System.out.println(savedFruit);
        return fruitMapper.toDTO(savedFruit);
    }

    public List<FruitResponseDTO> getAllFruits() {
        return fruitRepository.findAll()
                .stream()
                .map(fruitMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Fruit getFruitById(Long id) {
        return fruitRepository.findById(id).orElseThrow(() -> new FruitNotFoundException(id));
    }

    public FruitResponseDTO updateFruit(long id, FruitRequestDTO updatedFruit) {
        Fruit existingFruit = getFruitById(id);
        existingFruit = fruitMapper.toEntity(updatedFruit);
        existingFruit.setId(id);
        fruitRepository.save(existingFruit);
        return fruitMapper.toDTO(existingFruit);
    }


    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }
}


