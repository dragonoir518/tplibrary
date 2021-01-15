package fr.training.spring.Library.application;

import fr.training.spring.Library.infrastructure.LibraryDAO;
import fr.training.spring.Library.domaine.exceptions.LibraryNotFoundExeption;
import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryDAO libaryDAO;

    public Library findLibaryById(String id) {
       return libaryDAO.findById(id).orElseThrow( ()-> new LibraryNotFoundExeption("Libary not exists=>"+ id));
    }

    @Override
    public List<Library> findAll() {
     return (List<Library>) libaryDAO.findAll();
    }

    @Override
    public List<Library> findAllType(LibraryType type) {
     return libaryDAO.findByLibraryType(type);

    }

    @Override
    public List<Library> findAllByDirector(String prenom) {
       return libaryDAO.findByLibraryDirectorPrenom(prenom);
    }

    @Override
    public String deleteLibaray(String idLibary) {
        Library library = findLibaryById(idLibary);
        libaryDAO.delete(library);
        return idLibary;
    }

    public String createLibary(Library library) {
        libaryDAO.save(library);
        return library.getIdLibrary();
    }
    
}
