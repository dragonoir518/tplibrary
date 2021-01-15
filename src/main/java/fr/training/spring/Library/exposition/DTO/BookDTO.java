package fr.training.spring.Library.exposition.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.Library.domaine.Genre;

public class BookDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("nomberPage")
    private int nomberPage;

    @JsonProperty("genre")
    private Genre genre;

    public BookDTO(String title, String author, String isbn, int nomberPage, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.nomberPage = nomberPage;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNomberPage() {
        return nomberPage;
    }

    public void setNomberPage(int nomberPage) {
        this.nomberPage = nomberPage;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
