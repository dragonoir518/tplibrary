package fr.training.spring.Library.domaine;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;

    private String title;

    private String author;

    private String isbn;

    private int nomberPage;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Book() {}

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

    private Book(String title, String author, String isbn, int nomberPage, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.nomberPage = nomberPage;
        this.genre = genre;
    }


    public static final class Builder {
        private String title;
        private String author;
        private String isbn;
        private int nomberPage;
        private Genre genre;

        private Builder() {
        }

        public static Builder aBook() {
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

        public Book build() {
            return new Book(title, author, isbn, nomberPage, genre);
        }
    }
}
