package fr.training.spring.Library.exposition;

import fr.training.spring.Library.application.LibraryService;
import fr.training.spring.Library.domaine.*;
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
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/library" + "/LA75013", String.class);

        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.OK));

        //System.out.println(response.getBody().toString());
    }

    @Test
    public void notFound_Exception_Test(){
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/library" + "/TOTO", String.class);

        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.NOT_FOUND));
        assertThat(response.getBody()).contains("Libary not exists");
        System.out.println(response.getBody());
    }

    @Test
    public void test_API_All_Libaries(){
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/libraries", String.class);

        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.OK));

        System.out.println(response.getBody().toString());
    }

    @Test
    public void save_library_without_books_should_be_sucess() {


        LibraryDTO libraryDTO = LibraryDTO.Builder.aLibraryDTO()
                                .libraryType(LibraryType.Scolaire)
                                .idLibrary("LA88888")
                                .libraryDirectorDTO(new LibraryDirectorDTO("Jean","Dupont"))
                                .libraryAddressDTO(LibraryAddressDTO.Builder.aLibraryAddressDTO().numero(55).rue("rue de Parid").ville("Paris").codePostal(75012).build())
                                .build();
    //    libraryService.createLibary(libraryAdapter.mapToEntity(libraryDTO));

        restTemplate.postForEntity("/api/create/library/",libraryAdapter.mapToEntity(libraryDTO), String.class);

        final ResponseEntity<String> response = restTemplate.getForEntity("/api/library/LA88888", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());

    }

    @Test
    public void save_Livrary_With_Books_should_return_sucess() {
        List<Book> myBooks = new ArrayList<>();
        Book b1 = Book.Builder.aBook().author("VICTOR HUGO").genre(Genre.Literraire).isbn("FR256897SNG").nomberPage(150).title("les misérables").build();
        Book b2 = Book.Builder.aBook().author("EMILE ZOLA").genre(Genre.Literraire).isbn("FR256897DGG").nomberPage(250).title("Germinal").build();

        myBooks.add(b1);
        myBooks.add(b2);

        Library library = Library.Builder.aLibrary()
                        .idLibrary("LA99999")
                        .libraryType(LibraryType.Scolaire)
                        .libraryDirector(new LibraryDirector("Jean", "Dupont"))
                        .libraryAddress(new LibraryAddress(55,"Rue de Paris",75012,"Paris"))
                        .books(myBooks)
                        .build();

        libraryService.createLibary(library);

        final ResponseEntity<Library> response = restTemplate.getForEntity("/api/library/LA99999", Library.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getBooks().size()).isEqualTo(2);
        assertThat(response.getBody().getIdLibrary()).isEqualTo("LA99999");
    }

}
