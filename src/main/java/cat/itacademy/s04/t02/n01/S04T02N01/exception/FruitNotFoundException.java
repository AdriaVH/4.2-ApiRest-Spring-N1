package cat.itacademy.s04.t02.n01.S04T02N01.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(Long id) {
        super("Fruit with id " + id + " not found.");
    }
}
