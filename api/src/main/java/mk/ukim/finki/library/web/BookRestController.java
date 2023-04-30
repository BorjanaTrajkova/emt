package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.UpsertBookDto;
import mk.ukim.finki.library.model.enumerations.Category;
import mk.ukim.finki.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        List<Category> categories = Arrays.asList(Category.values());
        return categories;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> save(@RequestBody UpsertBookDto upsertBookDto) {
        return this.bookService.save(upsertBookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/editBook/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody UpsertBookDto upsertBookDto) {
        return this.bookService.edit(id, upsertBookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id) {
        return this.bookService.delete(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/setAvailableCopies/{id}")
    public ResponseEntity<Book> setAvailableCopies(@PathVariable Long id) {
        return this.bookService.setAvailableCopies(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
