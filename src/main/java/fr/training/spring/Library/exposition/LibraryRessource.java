package fr.training.spring.Library.exposition;

import fr.training.spring.Library.application.BookService;
import fr.training.spring.Library.application.LibraryService;
import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;
import fr.training.spring.Library.domaine.book.Book;
import fr.training.spring.Library.domaine.book.Genre;
import fr.training.spring.Library.exposition.DTO.BookLightDTO;
import fr.training.spring.Library.exposition.DTO.LibraryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LibraryRessource {


    @Autowired
    private LibraryService libaryService;

    @Autowired
    private LibraryAdapter libraryAdapter;

    @Autowired
    private BookService bookService;

    @PostMapping(value="/create/library", consumes = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public String createLibrary(@Valid @RequestBody LibraryDTO libraryDTO) {

        Library library = libraryAdapter.mapToEntity(libraryDTO);
        return libaryService.createLibary(library);  //retourner id de la libary
    }

    @GetMapping("/library/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LibraryDTO afficherLibraryById(@PathVariable("id") String idLibary){

        LibraryDTO dto = libraryAdapter.mapToDto(libaryService.findLibaryById(idLibary));
        return dto;
    }

    @GetMapping("/libraries")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> afficherAllLibraries(){
        List<LibraryDTO> listDto = libraryAdapter.mapToDtoList(libaryService.findAll());
        return listDto;
    }

    @GetMapping("/libraries/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> afficherAllLibrariesType(@PathVariable("type") final LibraryType type){

        List<LibraryDTO> listDto = libraryAdapter.mapToDtoList(libaryService.findAllType(type));

        return listDto;
    }

    @GetMapping("/libraries/director/{prenomDirector}")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> afficherAllLibrariesDirector(@PathVariable("prenomDirector") String prenom){
        List<LibraryDTO> listDto = libraryAdapter.mapToDtoList(libaryService.findAllByDirector(prenom));
        return listDto;
    }

    @DeleteMapping("/delete/labrary/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletLibaray(@PathVariable("id") String idLibrary){
        return libaryService.deleteLibaray(idLibrary);
    }

    @PutMapping("/update/library/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateLibary(@PathVariable("id") final String idLibary, @RequestBody LibraryDTO libraryDTO) {
        return libaryService.updateLibrary(idLibary, libraryAdapter.mapToEntity(libraryDTO));
    }


    @PostMapping("/library/{idLibrary}/book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookToLibrary(@PathVariable("idLibrary") final String idLibrary,
                                   @RequestBody final BookLightDTO bookDTOLight) {
        String isbn = bookDTOLight.getIsbn();
        Genre genre = bookDTOLight.getGenre();

     libaryService.referenceBook(idLibrary,isbn, genre);


    }


}
