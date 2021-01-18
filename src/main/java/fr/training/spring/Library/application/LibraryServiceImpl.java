package fr.training.spring.Library.application;

import fr.training.spring.Library.domaine.LibraryRepository;
import fr.training.spring.Library.domaine.book.Book;
import fr.training.spring.Library.domaine.book.Genre;
import fr.training.spring.Library.domaine.ddd.DDD;
import fr.training.spring.Library.infrastructure.LibraryDAO;
import fr.training.spring.Library.domaine.exceptions.LibraryNotFoundExeption;
import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DDD.ApplicationService
@Transactional
@Service
public class LibraryServiceImpl implements LibraryService {

    //@Autowired   //Ici la couche Application dépendant de la couche Infrastructure
    //private LibraryDAO libaryDAO;
    //*** On bascule Application dépendant de la couche Domaine qui a une interface Repository
   // @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookService bookService;

    public Library findLibaryById(String id) {
       //return libaryDAO.findById(id).orElseThrow( ()-> new LibraryNotFoundExeption("Libary not exists=>"+ id));
        return libraryRepository.findLibaryById(id);
    }

    @Override
    public List<Library> findAll() {
        //return (List<Library>) libaryDAO.findAll();
        return libraryRepository.findAll();
    }

    @Override
    public List<Library> findAllType(LibraryType type) {
     //return libaryDAO.findByLibraryType(type);
        return libraryRepository.findAllType(type);

    }

    @Override
    public List<Library> findAllByDirector(String prenom) {

        //return libaryDAO.findByLibraryDirectorPrenom(prenom);
        return libraryRepository.findAllByDirector(prenom);
    }

    @Override
    public String deleteLibaray(String idLibary) {
        //Library library = findLibaryById(idLibary);
        //libaryDAO.delete(library);
        //return idLibary;
        return libraryRepository.deleteLibaray(idLibary);
    }

    @Override
    public String updateLibrary(String idLibary, Library newlibrary) {
        Library library = libraryRepository.findLibaryById(idLibary);
        library.update(newlibrary);
        libraryRepository.createLibary(library);
        return library.getIdLibrary();
    }

    @Override
    public void referenceBook(String idLibrary, String isbn, Genre genre) {
        Book book = bookService.searchBookByISBN(isbn);
        book.assignGenre(genre);
        Library library = libraryRepository.findLibaryById(idLibrary);
        library.addBook(book);
        libraryRepository.createLibary(library);

    }

    public String createLibary(Library library) {
        //libaryDAO.save(library);
        //return library.getIdLibrary();
        return libraryRepository.createLibary(library);
    }
    
}
