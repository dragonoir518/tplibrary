package fr.training.spring.Library.batch.exportjob;

public class BookLibraryDTO {

    private String author;

    private String title;

    private String isbn;

    private String genre;

    private int page;

    private int numerADR;

    private String street;

    private int cp;

    private String city;

    private String idLibrary;

    public BookLibraryDTO() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNumerADR() {
        return numerADR;
    }

    public void setNumerADR(int numerADR) {
        this.numerADR = numerADR;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(String idLibrary) {
        this.idLibrary = idLibrary;
    }
}
