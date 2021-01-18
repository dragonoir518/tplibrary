package fr.training.spring.Library.exposition;

import fr.training.spring.Library.application.BookService;
import fr.training.spring.Library.domaine.book.Book;
import fr.training.spring.Library.exposition.DTO.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookRessource {

    @Autowired
    private BookService bookService;



    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO searchBookByISBN(@RequestParam("isbn") final String isbn) {
        final Book book = bookService.searchBookByISBN(isbn);
        return BookDTO.Builder.aBookDTO()
                .author(book.getAuthor())
                .isbn(isbn)
                .nomberPage(book.getNomberPage())
                .title(book.getTitle())
                .build();
    }


}
