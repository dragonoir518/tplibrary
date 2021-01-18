package fr.training.spring.Library.domaine;

import fr.training.spring.Library.domaine.ddd.DDD;

import java.util.List;

@DDD.Repository
public interface LibraryRepository {

    String createLibary(Library library);
    Library findLibaryById(String id);
    List<Library> findAll();
    List<Library> findAllType(LibraryType type);
    List<Library> findAllByDirector(String prenom);
    String deleteLibaray(String idLibary);
}
