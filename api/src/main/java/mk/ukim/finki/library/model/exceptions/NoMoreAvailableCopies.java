package mk.ukim.finki.library.model.exceptions;

public class NoMoreAvailableCopies extends RuntimeException {
    public NoMoreAvailableCopies() {
        super(String.format("No more available copies"));
    }
}
