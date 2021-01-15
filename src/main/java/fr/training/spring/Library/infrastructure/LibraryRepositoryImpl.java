package fr.training.spring.Library.infrastructure;

import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryRepository;
import fr.training.spring.Library.domaine.LibraryType;
import fr.training.spring.Library.domaine.exceptions.LibraryNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

    @Autowired
    private LibraryDAO dao;

    @Override
    public String createLibary(Library library) {

        dao.save(LibraryJPA.toLibraryJPA(library));
        return library.getIdLibrary();
    }

    @Override
    public Library findLibaryById(String id) {
        return dao.findById(id).map(LibraryJPA::toLibrary).orElseThrow( ()-> new LibraryNotFoundExeption("Libary not exists=>"+ id));
    }

    @Override
    public List<Library> findAll() {
        return dao.findAll().stream().map(LibraryJPA::toLibrary).collect(Collectors.toList());
    }

    @Override
    public List<Library> findAllType(LibraryType type) {
        return dao.findByLibraryType(type).stream().map(LibraryJPA::toLibrary).collect(Collectors.toList());
    }

    @Override
    public List<Library> findAllByDirector(String prenom) {
       return dao.findByLibraryDirectorPrenom(prenom).stream().map(LibraryJPA::toLibrary).collect(Collectors.toList());
    }

    @Override
    public String deleteLibaray(String idLibary) {
        Library library = findLibaryById(idLibary);
        dao.delete(LibraryJPA.toLibraryJPA(library));
        return idLibary;
    }
}
