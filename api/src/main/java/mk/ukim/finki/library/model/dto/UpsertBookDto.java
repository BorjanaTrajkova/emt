package mk.ukim.finki.library.model.dto;

import lombok.Data;
import mk.ukim.finki.library.model.enumerations.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UpsertBookDto {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long author;

    private Integer availableCopies;

    public UpsertBookDto() {
    }

    public UpsertBookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
