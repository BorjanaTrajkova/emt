package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository
                .findById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public Optional<Author> createAuthor(Author author) {
        return Optional.of(this.authorRepository.save(author));
    }
}
