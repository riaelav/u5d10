package valeriapagliarini.u5d10.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Element with ID " + id + " not found.");
    }
}
