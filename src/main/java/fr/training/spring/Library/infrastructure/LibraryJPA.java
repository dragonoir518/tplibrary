package fr.training.spring.Library.infrastructure;

import fr.training.spring.Library.domaine.*;
import fr.training.spring.Library.domaine.book.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LIBRARY")
public class LibraryJPA {
    //ici on peut mettre la classe à l'image de la BDD pas forcement en entity
    @Id
    private String idLibrary;

    @Enumerated(EnumType.STRING)
    private LibraryType libraryType;

    @Embedded
    private LibraryAddress libraryAddress;

    @Embedded
    @NotNull
    private LibraryDirector libraryDirector;

    @OneToMany(cascade= CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookJPA> books;


    public static LibraryJPA toLibraryJPA(Library library) {
        //transformer Objet Libraty to LibratyJPA pour save dans BDD
        LibraryJPA libraryJPA= new LibraryJPA();
        libraryJPA.setIdLibrary(library.getIdLibrary());
        libraryJPA.setLibraryAddress(library.getLibraryAddress());
        libraryJPA.setLibraryType(library.getLibraryType());
        libraryJPA.setLibraryDirector(library.getLibraryDirector());
        if(library.getBooks() != null) {
            libraryJPA.setBooks(library.getBooks().stream().map(BookJPA::new).collect(Collectors.toList()));
        }

        return libraryJPA;
    }

    public static Library toLibrary(LibraryJPA libraryJPA) {
        //transformer LibraryJPA vers Library
        List<Book> books = new ArrayList<>();
        if(libraryJPA.getBooks() != null) {
            books=libraryJPA.getBooks().stream().map(b-> new Book(b.getTitle(),
                    b.getAuthor(),
                    b.getIsbn(),
                    b.getNomberPage(),
                    b.getGenre()))
                    .collect(Collectors.toList());
        }
        else {
            books = null;
        }

        Library l = Library.Builder.aLibrary()
                        .idLibrary(libraryJPA.getIdLibrary())
                        .libraryType(libraryJPA.getLibraryType())
                        .libraryAddress(libraryJPA.getLibraryAddress())
                        .libraryDirector(libraryJPA.getLibraryDirector())
                        .books(books)
                        .build();

        return l;

    }

    public LibraryJPA() {
        //constructeur par défaut
    }

    public String getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(String idLibrary) {
        this.idLibrary = idLibrary;
    }

    public LibraryType getLibraryType() {
        return libraryType;
    }

    public void setLibraryType(LibraryType libraryType) {
        this.libraryType = libraryType;
    }

    public LibraryAddress getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(LibraryAddress libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    public LibraryDirector getLibraryDirector() {
        return libraryDirector;
    }

    public void setLibraryDirector(LibraryDirector libraryDirector) {
        this.libraryDirector = libraryDirector;
    }

    public List<BookJPA> getBooks() {
        return books;
    }

    public void setBooks(List<BookJPA> books) {
        this.books = books;
    }
}
