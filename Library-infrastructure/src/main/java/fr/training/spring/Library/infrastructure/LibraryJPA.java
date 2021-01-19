package fr.training.spring.Library.infrastructure;

import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryAddress;
import fr.training.spring.Library.domaine.LibraryDirector;
import fr.training.spring.Library.domaine.LibraryType;
import fr.training.spring.Library.domaine.book.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "LIBRARY")
public class LibraryJPA {
    //ici on peut mettre la classe à l'image de la BDD pas forcement en entity
    @Id
    private String idLibrary;

    @Enumerated(EnumType.STRING)
    private LibraryType libraryType;

    @Column
    private int addressNumber;

    @Column
    private String addressStreet;

    @Column
    private int addressPostalCode;

    @Column
    private String addressCity;

    @Column
    private String libraryDirectorNom;

    @Column
    private String libraryDirectorPrenom;

    @OneToMany(cascade= CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookJPA> books;

    public LibraryJPA() {
        //constructeur par défaut
    }

    public static LibraryJPA toLibraryJPA(Library library) {
        //transformer Objet Libraty to LibratyJPA pour save dans BDD
        LibraryJPA libraryJPA= new LibraryJPA();
        libraryJPA.setIdLibrary(library.getIdLibrary());
        libraryJPA.setAddressNumber(library.getLibraryAddress().getNumero());
        libraryJPA.setAddressStreet(library.getLibraryAddress().getRue());
        libraryJPA.setAddressCity(library.getLibraryAddress().getVille());
        libraryJPA.setAddressPostalCode(library.getLibraryAddress().getCodePostal());
        libraryJPA.setLibraryType(library.getLibraryType());
        libraryJPA.setLibraryDirectorNom(library.getLibraryDirector().getNom());
        libraryJPA.setLibraryDirectorPrenom(library.getLibraryDirector().getPrenom());
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

        LibraryAddress address = new LibraryAddress(libraryJPA.addressNumber,
                                                    libraryJPA.addressStreet,
                                                    libraryJPA.addressPostalCode,
                                                    libraryJPA.addressCity);

        LibraryDirector director = new LibraryDirector(libraryJPA.libraryDirectorPrenom,libraryJPA.libraryDirectorNom);
        Library l = Library.Builder.aLibrary()
                        .idLibrary(libraryJPA.getIdLibrary())
                        .libraryType(libraryJPA.getLibraryType())
                        .libraryAddress(address)
                        .libraryDirector(director)
                        .books(books)
                        .build();

        return l;

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

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public int getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(int addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getLibraryDirectorNom() {
        return libraryDirectorNom;
    }

    public void setLibraryDirectorNom(String libraryDirectorNom) {
        this.libraryDirectorNom = libraryDirectorNom;
    }

    public String getLibraryDirectorPrenom() {
        return libraryDirectorPrenom;
    }

    public void setLibraryDirectorPrenom(String libraryDirectorPrenom) {
        this.libraryDirectorPrenom = libraryDirectorPrenom;
    }

    public List<BookJPA> getBooks() {
        return books;
    }

    public void setBooks(List<BookJPA> books) {
        this.books = books;
    }
}
