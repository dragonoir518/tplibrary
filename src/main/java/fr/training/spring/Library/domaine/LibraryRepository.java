package fr.training.spring.Library.domaine;

import java.util.List;


public interface LibraryRepository {

    String createLibary(Library library);
    Library findLibaryById(String id);
    List<Library> findAll();
    List<Library> findAllType(LibraryType type);
    List<Library> findAllByDirector(String prenom);
    String deleteLibaray(String idLibary);
}
