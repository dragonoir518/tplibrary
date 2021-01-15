package fr.training.spring.Library.infrastructure;

import fr.training.spring.Library.domaine.Book;
import fr.training.spring.Library.domaine.Genre;

import javax.persistence.*;

@Entity
@Table(name="BOOK")
public class BookJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;

    private String title;

    private String author;

    private String isbn;

    private int nomberPage;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private BookJPA() {}

    public BookJPA(final Book book) {
        this.idBook = book.getIdBook();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.nomberPage = book.getNomberPage();
        this.genre = book.getGenre();
    }


    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
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
