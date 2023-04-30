package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.UpsertBookDto;
import mk.ukim.finki.library.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> createBook(String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> save(UpsertBookDto upsertBookDto);

    Optional<Book> edit(Long id, UpsertBookDto upsertBookDto);

    Optional<Book> delete(Long id);

    Optional<Book> setAvailableCopies(Long id);
}
