package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> createAuthor(Author author);
}
