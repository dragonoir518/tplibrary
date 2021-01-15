package fr.training.spring.Library.exposition.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.Library.domaine.Book;
import fr.training.spring.Library.domaine.LibraryAddress;
import fr.training.spring.Library.domaine.LibraryDirector;
import fr.training.spring.Library.domaine.LibraryType;

import java.util.List;

public class LibraryDTO {

    @JsonProperty
    private String idLibrary;

    @JsonProperty("libraryType")
    private LibraryType libraryType;

    @JsonProperty("libraryAddress")
    private LibraryAddressDTO libraryAddressDTO;

    @JsonProperty("libraryDirector")
    private LibraryDirectorDTO libraryDirectorDTO;

    @JsonProperty("books")
    private List<BookDTO> booksDTO;


    public LibraryType getLibraryType() {
        return libraryType;
    }

    public String getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(String idLibrary) {
        this.idLibrary = idLibrary;
    }

    public void setLibraryType(LibraryType libraryType) {
        this.libraryType = libraryType;
    }

    public LibraryAddressDTO getLibraryAddressDTO() {
        return libraryAddressDTO;
    }

    public void setLibraryAddressDTO(LibraryAddressDTO libraryAddressDTO) {
        this.libraryAddressDTO = libraryAddressDTO;
    }

    public LibraryDirectorDTO getLibraryDirectorDTO() {
        return libraryDirectorDTO;
    }

    public void setLibraryDirectorDTO(LibraryDirectorDTO libraryDirectorDTO) {
        this.libraryDirectorDTO = libraryDirectorDTO;
    }

    public List<BookDTO> getBooksDTO() {
        return booksDTO;
    }

    public void setBooksDTO(List<BookDTO> booksDTO) {
        this.booksDTO = booksDTO;
    }


    public static final class Builder {
        private String idLibrary;
        private LibraryType libraryType;
        private LibraryAddressDTO libraryAddressDTO;
        private LibraryDirectorDTO libraryDirectorDTO;
        private List<BookDTO> booksDTO;

        private Builder() {
        }

        public static Builder aLibraryDTO() {
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

        public Builder libraryAddressDTO(LibraryAddressDTO libraryAddressDTO) {
            this.libraryAddressDTO = libraryAddressDTO;
            return this;
        }

        public Builder libraryDirectorDTO(LibraryDirectorDTO libraryDirectorDTO) {
            this.libraryDirectorDTO = libraryDirectorDTO;
            return this;
        }

        public Builder booksDTO(List<BookDTO> booksDTO) {
            this.booksDTO = booksDTO;
            return this;
        }

        public LibraryDTO build() {
            LibraryDTO libraryDTO = new LibraryDTO();
            libraryDTO.setIdLibrary(idLibrary);
            libraryDTO.setLibraryType(libraryType);
            libraryDTO.setLibraryAddressDTO(libraryAddressDTO);
            libraryDTO.setLibraryDirectorDTO(libraryDirectorDTO);
            libraryDTO.setBooksDTO(booksDTO);
            return libraryDTO;
        }
    }
}
