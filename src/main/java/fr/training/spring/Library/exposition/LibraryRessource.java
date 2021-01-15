package fr.training.spring.Library.exposition;

import fr.training.spring.Library.application.LibraryService;
import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;
import fr.training.spring.Library.exposition.DTO.LibraryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryRessource {


    @Autowired
    private LibraryService libaryService;

    @Autowired
    private LibraryAdapter libraryAdapter;

    @PostMapping("/create/library")
    public String createLibary(@RequestBody LibraryDTO libraryDTO) {
        Library library = libraryAdapter.mapToEntity(libraryDTO);
        return libaryService.createLibary(library);  //retourner id de la libary
    }

    @GetMapping("/library/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LibraryDTO afficherLibaryById(@PathVariable("id") String idLibary){

        LibraryDTO dto = libraryAdapter.mapToDto(libaryService.findLibaryById(idLibary));
        return dto;
    }

    @GetMapping("/libraries")
    public List<LibraryDTO> afficherAllLibaries(){
        List<LibraryDTO> listDto = libraryAdapter.mapToDtoList(libaryService.findAll());
        return listDto;
    }

    @GetMapping("/libraries/type/{type}")
    public List<LibraryDTO> afficherAllLibariesType(@PathVariable("type") final LibraryType type){

        List<LibraryDTO> listDto = libraryAdapter.mapToDtoList(libaryService.findAllType(type));

        return listDto;
    }

    @GetMapping("/libraries/director/{prenomDirector}")
    public List<LibraryDTO> afficherAllLibariesDirector(@PathVariable("prenomDirector") String prenom){
        List<LibraryDTO> listDto = libraryAdapter.mapToDtoList(libaryService.findAllByDirector(prenom));
        return listDto;
    }

    @DeleteMapping("/delete/labrary/{id}")
    public String deletLibaray(@PathVariable("id") String idLibary){
        return libaryService.deleteLibaray(idLibary);
    }

    @PutMapping("/majlibrary")
    public String updateLibary(@RequestBody Library library) {
        return libaryService.createLibary(library);
    }

}
