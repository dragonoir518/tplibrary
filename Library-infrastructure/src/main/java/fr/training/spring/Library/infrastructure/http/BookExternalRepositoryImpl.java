package fr.training.spring.Library.infrastructure.http;

import fr.training.spring.Library.domaine.book.Book;
import fr.training.spring.Library.domaine.book.BookRepository;
import fr.training.spring.Library.domaine.ddd.DDD;
import fr.training.spring.Library.domaine.exceptions.ErrorCodes;
import fr.training.spring.Library.domaine.exceptions.NotFoundException;
import fr.training.spring.Library.domaine.exceptions.OpenLibraryTechnicalException;
import fr.training.spring.Library.infrastructure.http.dto.AuthorInfo;
import fr.training.spring.Library.infrastructure.http.dto.BookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@DDD.Repository
@Repository
public class BookExternalRepositoryImpl implements BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookExternalRepositoryImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Book searchBook(String isbn) {
        try {
            final ResponseEntity<BookInfo> response = restTemplate.getForEntity("/isbn/" + isbn + ".json",
                    BookInfo.class);



            final BookInfo bookInfo = response.getBody();

            logger.debug(bookInfo.toString());


            final String authorName = serachAuthor(bookInfo.getAuthors().get(0).getKey()) ;

           // return new Book(null, isbn, bookInfo.getTitle(), authorName, bookInfo.getNumber_of_pages(), null);
            return Book.Builder.aBook()
                        .isbn(isbn)
                        .author(authorName)
                        .nomberPage(bookInfo.getNumber_of_pages())
                        .title(bookInfo.getTitle())
                        .build();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new NotFoundException("Book isbn " + isbn + " not found in openlibrary.org",
                        ErrorCodes.BOOK_NOT_FOUND);
            }
            throw new OpenLibraryTechnicalException(e);
        }
    }

    private String serachAuthor(String key) {
        String authoName = "Unkwown";

        if(!key.isEmpty()) {
            try {
                final ResponseEntity<AuthorInfo> resAuthor = restTemplate.getForEntity(key + ".json",
                        AuthorInfo.class);
                final AuthorInfo authorInfo = resAuthor.getBody();
                authoName = authorInfo.getName();
            } catch(RestClientException e) {
                logger.error("Erro on author call for "+ key);
            }
        }

        return authoName;
    }
}
