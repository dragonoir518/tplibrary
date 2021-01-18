package fr.training.spring.Library.exposition.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.Library.domaine.book.Genre;

public class BookLightDTO {

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("genre")
    private Genre genre;

    public String getIsbn() {
        return isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public BookLightDTO(String isbn, Genre genre) {
        this.isbn = isbn;
        this.genre = genre;
    }
}
