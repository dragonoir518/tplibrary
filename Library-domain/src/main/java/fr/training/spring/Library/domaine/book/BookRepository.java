package fr.training.spring.Library.domaine.book;

import fr.training.spring.Library.domaine.ddd.DDD;

@DDD.Repository
public interface BookRepository {
    Book searchBook(String isbn);
}
