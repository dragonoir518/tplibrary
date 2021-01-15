package fr.training.spring.Library.domaine;

import javax.persistence.*;
import java.util.List;

@Entity
public class Library {
    @Id
    private String idLibrary;

    @Enumerated(EnumType.STRING)
    private LibraryType libraryType;

    @Embedded
    private LibraryAddress libraryAddress;

    @Embedded
    private LibraryDirector libraryDirector;

    @OneToMany(cascade= CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books;


    public Library() {
        //constructeur par défaut
    }
    public Library(String idLibrary, LibraryType libraryType, LibraryAddress libraryAddress, LibraryDirector libraryDirector) {
        this.idLibrary = idLibrary;
        this.libraryType = libraryType;
        this.libraryAddress = libraryAddress;
        this.libraryDirector = libraryDirector;
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


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static final class Builder {
        private String idLibrary;
        private LibraryType libraryType;
        private LibraryAddress libraryAddress;
        private LibraryDirector libraryDirector;
        private List<Book> books;

        private Builder() {
        }

        public static Builder aLibrary() {
            return new Builder();
        }

        public Builder idLibrary(String idLibrary) {
            this.idLibrary = idLibrary;
            return this;
        }

        public Builder libraryType(LibraryType libraryType) {
            this.libraryType = libraryType;
            return this;
        }

        public Builder libraryAddress(LibraryAddress libraryAddress) {
            this.libraryAddress = libraryAddress;
            return this;
        }

        public Builder libraryDirector(LibraryDirector libraryDirector) {
            this.libraryDirector = libraryDirector;
            return this;
        }

        public Builder books(List<Book> books) {
            this.books = books;
            return this;
        }

        public Library build() {
            Library library = new Library();
            library.setIdLibrary(idLibrary);
            library.setLibraryType(libraryType);
            library.setLibraryAddress(libraryAddress);
            library.setLibraryDirector(libraryDirector);
            library.books = this.books;
            return library;
        }
    }
}
