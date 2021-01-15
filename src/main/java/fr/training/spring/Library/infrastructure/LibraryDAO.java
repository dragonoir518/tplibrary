package fr.training.spring.Library.infrastructure;

import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryDAO extends JpaRepository<Library,String> {
        List<Library> findByLibraryType(LibraryType type);
        List<Library> findByLibraryDirectorPrenom(String prenom);
}
