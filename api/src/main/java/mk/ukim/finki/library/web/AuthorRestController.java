package mk.ukim.finki.library.web;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping("/add")
//    public ResponseEntity<Author> create(@RequestParam String name,
//                                         @RequestParam String surname,
//                                         @RequestParam Long countryId) {
//        return this.authorService.create(name, surname, countryId)
//                .map(author -> ResponseEntity.ok().body(author))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

}
