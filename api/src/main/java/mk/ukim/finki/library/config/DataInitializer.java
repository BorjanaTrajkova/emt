package mk.ukim.finki.library.config;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.model.enumerations.Category;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final BookService bookService;

    public DataInitializer(AuthorService authorService, CountryService countryService, BookService bookService) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void initData() {
        Country country1 = new Country("England", "Europe");
        Country country2 = new Country("Illinois", "North America");
        Country country3 = new Country("Brazil", "South America");


        countryService.createCountry(country1);
        countryService.createCountry(country2);
        countryService.createCountry(country3);


        Author author1 = new Author("Agatha", "Chrstie", country1);
        Author author2 = new Author("Ernest", "Hemingway", country2);
        Author author3 = new Author("Paulo", "Coelho", country3);


        authorService.createAuthor(author1);
        authorService.createAuthor(author2);
        authorService.createAuthor(author3);


        this.bookService.createBook("Death on the Nile", Category.NOVEL, author1.getId(), 2);
        this.bookService.createBook("The Sun Also Rises", Category.NOVEL, author2.getId(), 4);
        this.bookService.createBook("The Alchemist", Category.DRAMA, author3.getId(), 1);
        this.bookService.createBook("The Mysterious Affair at Styles", Category.THRILLER, author1.getId(), 1);
        this.bookService.createBook("House of Sky and Breath", Category.CLASSICS, author2.getId(), 1);
    }
}
