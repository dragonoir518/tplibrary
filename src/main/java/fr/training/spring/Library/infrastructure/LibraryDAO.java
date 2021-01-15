package fr.training.spring.Library.infrastructure;

import fr.training.spring.Library.domaine.Library;
import fr.training.spring.Library.domaine.LibraryType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface LibraryDAO extends JpaRepository<LibraryJPA,String> {
        List<LibraryJPA> findByLibraryType(LibraryType type);
        List<LibraryJPA> findByLibraryDirectorPrenom(String prenom);
}
