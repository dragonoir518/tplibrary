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


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LibraryAdapter {
    public static Library mapToEntity(LibraryDTO dto) {
        //construire objet LibraryDictor avec constructeur
        LibraryDirector director = new LibraryDirector();
        if(dto.getLibraryDirectorDTO() != null) {
            //library.setLibraryDirector(new LibraryDirector(dto.getLibraryDirectorDTO().getPrenom(),dto.getLibraryDirectorDTO().getNom()));
            director=new LibraryDirector(dto.getLibraryDirectorDTO().getPrenom(),dto.getLibraryDirectorDTO().getNom());
        }
        else {
            director=null;
        }

        //construire objet LibraryAddress avec constructeur (à remplacer par Builder plus tard)
        LibraryAddressDTO adrDTO = dto.getLibraryAddressDTO();

        //construire objet Book par Builder
        List<Book> books = new ArrayList<>();
        if(dto.getBooksDTO() != null) {
           // List<Book> books = dto.getBooksDTO().stream().filter(Objects::nonNull).map(b -> Book.Builder.aBook().isbn(b.getIsbn()).author(b.getAuthor()).nomberPage(b.getNomberPage()).genre(b.getGenre()).build()).collect(Collectors.toList());
           // library.setBooks(books);
            books = dto.getBooksDTO().stream().filter(Objects::nonNull).map(b -> Book.Builder.aBook().isbn(b.getIsbn()).author(b.getAuthor()).nomberPage(b.getNomberPage()).genre(b.getGenre()).build()).collect(Collectors.toList());
        }
        else {
            books = null;
        }

        Library library = Library.Builder.aLibrary()
                                .idLibrary(dto.getIdLibrary())
                                .libraryType(dto.getLibraryType())
                                .libraryDirector(director)
                                .libraryAddress(new LibraryAddress(adrDTO.getNumero(),adrDTO.getRue(),adrDTO.getCodePostal(),adrDTO.getVille()))
                                .books(books)
                                .build();
      //  library.setIdLibrary(dto.getIdLibrary());
      //  library.setLibraryType(dto.getLibraryType());

      //  library.setLibraryAddress(new LibraryAddress(adrDTO.getNumero(),adrDTO.getRue(),adrDTO.getCodePostal(),adrDTO.getVille()));

        return library;
    }

    public static LibraryDTO mapToDto(Library entity) {
        //LibraryDTO dto = new LibraryDTO();

        //pour construire address
        LibraryAddress adr = entity.getLibraryAddress();

       //pour directeur
        LibraryDirectorDTO directorDTO;
        if(entity.getLibraryDirector()!=null) {
             directorDTO = new LibraryDirectorDTO(entity.getLibraryDirector().getPrenom(),entity.getLibraryDirector().getNom());
        } else {
             directorDTO= null;
        }

        //pour construire Book
        List<BookDTO> booksDTO = entity.getBooks().stream().map(b -> new BookDTO(b.getTitle(),b.getAuthor(),b.getIsbn(),b.getNomberPage(),b.getGenre())).collect(Collectors.toList());

        LibraryDTO dto = LibraryDTO.Builder.aLibraryDTO()
                                            .idLibrary(entity.getIdLibrary())
                                            .libraryType(entity.getLibraryType())
                                            .libraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(adr.getNumero()).rue(adr.getRue()).codePostal(adr.getCodePostal()).ville(adr.getVille()).build())
                                            .libraryDirectorDTO(directorDTO)
                                            .booksDTO(booksDTO)
                                            .build();
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
