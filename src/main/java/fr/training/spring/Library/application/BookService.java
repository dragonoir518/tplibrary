package fr.training.spring.Library.application;

import fr.training.spring.Library.domaine.book.Book;
import fr.training.spring.Library.domaine.book.BookRepository;
import fr.training.spring.Library.domaine.ddd.DDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DDD.ApplicationService
@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book searchBookByISBN(final String isbn){
        return bookRepository.searchBook(isbn);
    }


}
