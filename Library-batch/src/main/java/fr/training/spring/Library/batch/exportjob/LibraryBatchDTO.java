package fr.training.spring.Library.batch.exportjob;


import fr.training.spring.Library.domaine.LibraryType;



public class LibraryBatchDTO {

    private String idLibrary;

    private LibraryType libraryType;

    private int addressNumber;

    private String addressStreet;

    private int addressPostalCode;

    private String addressCity;

    private String libraryDirectorNom;

    private String libraryDirectorPrenom;


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

}
