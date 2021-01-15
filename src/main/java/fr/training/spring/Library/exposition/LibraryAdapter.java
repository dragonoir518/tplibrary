package fr.training.spring.Library.exposition;

import fr.training.spring.Library.domaine.Book;
import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryAddress;
import fr.training.spring.Library.domaine.LibraryDirector;
import fr.training.spring.Library.exposition.DTO.BookDTO;
import fr.training.spring.Library.exposition.DTO.LibraryAddressDTO;
import fr.training.spring.Library.exposition.DTO.LibraryDTO;
import fr.training.spring.Library.exposition.DTO.LibraryDirectorDTO;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LibraryAdapter {
    public static Library mapToEntity(LibraryDTO dto) {
        Library library = new Library();
        library.setIdLibrary(dto.getIdLibrary());
        library.setLibraryType(dto.getLibraryType());
        //construire objet LibraryDictor avec constructeur
        library.setLibraryDirector(new LibraryDirector(dto.getLibraryDirectorDTO().getPrenom(),dto.getLibraryDirectorDTO().getNom()));
        //construire objet LibraryAddress avec constructeur (à remplacer par Builder plus tard)
        LibraryAddressDTO adrDTO = dto.getLibraryAddressDTO();
        library.setLibraryAddress(new LibraryAddress(adrDTO.getNumero(),adrDTO.getRue(),adrDTO.getCodePostal(),adrDTO.getVille()));
        //construire objet Book par Builder
        if(dto.getBooksDTO() != null) {
            List<Book> books = dto.getBooksDTO().stream().filter(Objects::nonNull).map(b -> Book.Builder.aBook().isbn(b.getIsbn()).author(b.getAuthor()).nomberPage(b.getNomberPage()).genre(b.getGenre()).build()).collect(Collectors.toList());
            library.setBooks(books);
        }
        return library;
    }

    public static LibraryDTO mapToDto(Library entity) {
        LibraryDTO dto = new LibraryDTO();

        dto.setLibraryType(entity.getLibraryType());
        dto.setIdLibrary(entity.getIdLibrary());

        LibraryAddress adr = entity.getLibraryAddress();

        //dto.setLibraryAddressDTO(new LibraryAddressDTO(adr.getNumero(),adr.getRue(),adr.getCodePostal(), adr.getVille()));
        //construire AdressDTO via Builder
        dto.setLibraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(adr.getNumero()).rue(adr.getRue()).codePostal(adr.getCodePostal()).ville(adr.getVille()).build());
        dto.setLibraryDirectorDTO(new LibraryDirectorDTO(entity.getLibraryDirector().getPrenom(),entity.getLibraryDirector().getNom()));

        List<BookDTO> booksDTO = entity.getBooks().stream().map(b -> new BookDTO(b.getTitle(),b.getAuthor(),b.getIsbn(),b.getNomberPage(),b.getGenre())).collect(Collectors.toList());
        dto.setBooksDTO(booksDTO);
        return dto;
    }

    public List<LibraryDTO> mapToDtoList(final List<Library> entityList) {
       // return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
        return entityList.stream().map(LibraryAdapter::mapToDto).collect(Collectors.toList());
    }

    public List<Library> mapToEntityList(final List<LibraryDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(LibraryAdapter::mapToEntity).collect(Collectors.toList());
    }
}
