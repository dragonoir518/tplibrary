package fr.training.spring.Library.exposition.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.Library.domaine.book.Genre;

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

    public BookDTO() {
    }

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

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNomberPage() {
        return nomberPage;
    }

    public Genre getGenre() {
        return genre;
    }


    public static final class Builder {
        private String title;
        private String author;
        private String isbn;
        private int nomberPage;
        private Genre genre;

        private Builder() {
        }

        public static Builder aBookDTO() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder nomberPage(int nomberPage) {
            this.nomberPage = nomberPage;
            return this;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public BookDTO build() {
            return new BookDTO(title, author, isbn, nomberPage, genre);
        }
    }
}
