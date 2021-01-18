package fr.training.spring.Library.domaine;

import fr.training.spring.Library.domaine.book.Book;
import fr.training.spring.Library.domaine.ddd.DDD;
import fr.training.spring.Library.domaine.exceptions.ErrorCodes;
import fr.training.spring.Library.domaine.exceptions.ValidationException;

import java.util.List;

@DDD.Entity
public class Library {
    private String idLibrary;

    private LibraryType libraryType;

    private LibraryAddress libraryAddress;

    private LibraryDirector libraryDirector;

    private List<Book> books;


    public Library() {
        //constructeur par défaut
    }

    public void validate() {
        //on met la RG métier dans la classe LibraryDirector
        if(this.libraryDirector==null) {
            throw new ValidationException("Director is null", ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR);
        }
        libraryDirector.validate();
    }

    public Library(String idLibrary, LibraryType libraryType, LibraryAddress libraryAddress, LibraryDirector libraryDirector, List<Book> books) {
        this.idLibrary = idLibrary;
        this.libraryType = libraryType;
        this.libraryAddress = libraryAddress;
        this.libraryDirector = libraryDirector;
        this.books = books;
        validate();//vérifier le directeur n'est pas null
    }

    public void update(Library newlibrary) {
        this.libraryType = newlibrary.getLibraryType();
        this.libraryDirector = newlibrary.getLibraryDirector();
        this.libraryAddress = newlibrary.getLibraryAddress();
        validate(); //vérifier le directeur n'est pas null
    }

    public String getIdLibrary() {
        return idLibrary;
    }

    public LibraryType getLibraryType() {
        return libraryType;
    }

    public LibraryAddress getLibraryAddress() {
        return libraryAddress;
    }

    public LibraryDirector getLibraryDirector() {
        return libraryDirector;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
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
            return new Library(idLibrary, libraryType, libraryAddress, libraryDirector, books);
        }
    }


    @Override
    public String toString() {
        return "Library{" +
                "idLibrary='" + idLibrary + '\'' +
                ", libraryType=" + libraryType +
                ", libraryAddress=" + libraryAddress +
                ", libraryDirector=" + libraryDirector +
                ", books=" + books +
                '}';
    }
}
