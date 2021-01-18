package fr.training.spring.Library.exposition;

import fr.training.spring.Library.application.LibraryService;
import fr.training.spring.Library.domaine.*;
import fr.training.spring.Library.domaine.book.Genre;
import fr.training.spring.Library.domaine.exceptions.ValidationException;
import fr.training.spring.Library.exposition.DTO.BookDTO;
import fr.training.spring.Library.exposition.DTO.LibraryAddressDTO;
import fr.training.spring.Library.exposition.DTO.LibraryDTO;
import fr.training.spring.Library.exposition.DTO.LibraryDirectorDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


//lancer les tests avec un contexte Spring initialisé (ainsi que la base H2)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Test-Integration-API")
public class LibraryRessourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private LibraryAdapter libraryAdapter;

    @Test
    public void test_API_Afficher_Libary(){
        //Given
        String urlAPI="/api/library/";
        String idLibrary="LA75013";  //cet ID est chargé au démarrage de l'appli dans import.sql

        //When
        //** retour en String, on peut aussi faire le retour en objet Library
        ResponseEntity<String> response = restTemplate.getForEntity(urlAPI + idLibrary, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.OK));
        System.out.println(response.getBody().toString());
    }

    @Test
    public void notFound_Exception_Test(){
        //Given
        String urlAPI="/api/library/";
        String idLibrary="TOTO";

        //When
        ResponseEntity<String> response = restTemplate.getForEntity(urlAPI + idLibrary, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.NOT_FOUND));
       // assertThrows(Exception.class,()-> restTemplate.getForEntity(urlAPI + idLibrary, Exception.class));
        assertThat(response.getBody()).contains("Libary not exists");

        System.out.println(response.getBody());
    }


    @Test
    public void diretor_Is_Null_Exception_Test(){
        //Given
        String urlAPI = "/api/create/library/";
        String idLibrary = "LA00000";

        LibraryDTO libraryDTO = LibraryDTO.Builder.aLibraryDTO()
                .libraryType(LibraryType.Scolaire)
                .idLibrary(idLibrary)
                .libraryDirectorDTO(null) //si directeur est null on doit avoir une exception
               // .libraryDirectorDTO(new LibraryDirectorDTO("Jean","Dupont"))
                .libraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(55).rue("rue de Paris").ville("Paris").codePostal(75012).build())
                .build();

        //When
        //ResponseEntity<String> response = restTemplate.postForEntity(urlAPI,libraryAdapter.mapToEntity(libraryDTO), String.class);
         assertThrows(ValidationException.class,()-> restTemplate.postForEntity(urlAPI,libraryAdapter.mapToEntity(libraryDTO), ValidationException.class));

        //System.out.println(response);
        //Then

    }
    @Test
    public void update_A_Library_should_Be_Return_Sucess() {
        //Given
        String urlAPI="/api/update/library/";
        String idLibrary = "LA75013";

        LibraryDTO libraryDTO = LibraryDTO.Builder.aLibraryDTO()
                .libraryType(LibraryType.Scolaire)
                .idLibrary(idLibrary)
                .libraryDirectorDTO(new LibraryDirectorDTO("Jean_Update","Dupont_Update"))
                .libraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(9999).rue("rue de Paris Update").ville("Update").codePostal(75999).build())
                .build();

        //When
        /*final ResponseEntity<String> response = restTemplate.exchange("/api/update/library/"+idLibrary,
                HttpMethod.PUT, new HttpEntity<>(libraryDTO), String.class);

        System.out.println(response);*/
        restTemplate.put(urlAPI + idLibrary,libraryAdapter.mapToEntity(libraryDTO), String.class);

        //Then
        ResponseEntity<Library> responseFind = restTemplate.getForEntity("/api/library/" + idLibrary, Library.class);

        assertThat(responseFind.getStatusCode()).isEqualTo((HttpStatus.OK));
        assertThat(responseFind.getBody().getBooks().size()).isEqualTo(0);
        assertThat(responseFind.getBody().getLibraryAddress().getRue()).contains("Update");
        assertThat(responseFind.getBody().getLibraryDirector().getNom()).isEqualTo("Dupont_Update");
        System.out.println(responseFind.getBody().toString());

    }

    @Test
    public void test_API_All_Libaries(){
        //Given
        String urlAPI="/api/libraries";

        //When
        ResponseEntity<String> response = restTemplate.getForEntity(urlAPI, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.OK));
        System.out.println(response.getBody().toString());
    }

    @Test
    public void save_library_without_books_should_be_sucess() {
        //Given
        String urlAPI = "/api/create/library/";
        String idLibrary = "LA88888";

        LibraryDTO libraryDTO = LibraryDTO.Builder.aLibraryDTO()
                                .libraryType(LibraryType.Scolaire)
                                .idLibrary(idLibrary)
                               // .libraryDirectorDTO(null)
                                .libraryDirectorDTO(new LibraryDirectorDTO("Jean","Dupont"))
                                .libraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(55).rue("rue de Paris").ville("Paris").codePostal(75012).build())
                                .build();

        //When
        //    libraryService.createLibary(libraryAdapter.mapToEntity(libraryDTO));
        // appelle post pour envoyer la création
        restTemplate.postForEntity(urlAPI,libraryAdapter.mapToEntity(libraryDTO), String.class);

        //Then
        final ResponseEntity<String> response = restTemplate.getForEntity("/api/library/"+idLibrary, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains(idLibrary);
        System.out.println(response.getBody());

    }

    @Test
    public void save_Livrary_With_Books_should_return_sucess() {
        //Given
        String urlAPI="/api/create/library/";
        String idLibrary = "LA99999";
        List<BookDTO> myBooksDTO = new ArrayList<>();
        BookDTO b1 = BookDTO.Builder.aBookDTO().author("VICTOR HUGO").genre(Genre.Literraire).isbn("FR256897SNG").nomberPage(150).title("les misérables").build();
        BookDTO b2 = BookDTO.Builder.aBookDTO().author("EMILE ZOLA").genre(Genre.Literraire).isbn("FR256897DGG").nomberPage(250).title("Germinal").build();

        myBooksDTO.add(b1);
        myBooksDTO.add(b2);


        LibraryDTO libraryDTO = LibraryDTO.Builder.aLibraryDTO()
                .libraryType(LibraryType.Scolaire)
                .idLibrary(idLibrary)
                .libraryDirectorDTO(new LibraryDirectorDTO("Jean","Dupont"))
                .libraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(55).rue("rue de Paris").ville("Paris").codePostal(75012).build())
                .booksDTO(myBooksDTO)
                .build();

        //When
        // ** à remplacer par Post via API
        // libraryService.createLibary(libraryAdapter.mapToEntity(libraryDTO));
        restTemplate.postForEntity(urlAPI,libraryAdapter.mapToEntity(libraryDTO), String.class);

        //Then
        final ResponseEntity<Library> response = restTemplate.getForEntity("/api/library/"+idLibrary, Library.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getBooks().size()).isEqualTo(2);
        assertThat(response.getBody().getIdLibrary()).isEqualTo(idLibrary);
    }

}
