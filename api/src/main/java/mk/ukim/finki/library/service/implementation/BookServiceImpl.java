package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.UpsertBookDto;
import mk.ukim.finki.library.model.enumerations.Category;
import mk.ukim.finki.library.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.model.exceptions.BookNotFoundException;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Optional<Book> createBook(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(UpsertBookDto upsertBookDto) {
        Author author = this.authorRepository.findById(upsertBookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(upsertBookDto.getAuthor()));
        Book book = new Book(upsertBookDto.getName(), upsertBookDto.getCategory(), author, upsertBookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, UpsertBookDto upsertBookDto) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.getById(upsertBookDto.getAuthor());

        book.setName(upsertBookDto.getName());
        book.setCategory(upsertBookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(upsertBookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> setAvailableCopies(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
