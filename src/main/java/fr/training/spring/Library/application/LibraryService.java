package fr.training.spring.Library.application;

import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;

import java.util.List;

public interface LibraryService {
    String createLibary(Library library);
    Library findLibaryById(String id);
    List<Library> findAll();
    List<Library> findAllType(LibraryType type);
    List<Library> findAllByDirector(String prenom);
    String deleteLibaray(String idLibary);
}
