package fr.training.spring.Library.batch.exportjob;


import fr.training.spring.Library.application.LibraryService;
import fr.training.spring.Library.domaine.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;



@Component
@StepScope
public class LibraryProcessor implements ItemProcessor<String,LibraryBatchDTO> {

    private static final Logger logger = LoggerFactory.getLogger(LibraryProcessor.class);

    @Autowired
    private LibraryService libraryService;

    @Override
    public LibraryBatchDTO process(String idLibrary) throws Exception {
        final Library library = libraryService.findLibaryById(idLibrary);
        logger.info("Libray processing {}", library);

        final LibraryBatchDTO libraryBatchDTO = new LibraryBatchDTO();
        libraryBatchDTO.setIdLibrary(library.getIdLibrary());
        libraryBatchDTO.setLibraryType(library.getLibraryType());
        libraryBatchDTO.setAddressNumber(library.getLibraryAddress().getNumero());
        libraryBatchDTO.setAddressStreet(library.getLibraryAddress().getRue());
        libraryBatchDTO.setAddressCity(library.getLibraryAddress().getVille());
        libraryBatchDTO.setAddressPostalCode(library.getLibraryAddress().getCodePostal());
        libraryBatchDTO.setLibraryDirectorNom(library.getLibraryDirector().getNom());
        libraryBatchDTO.setLibraryDirectorPrenom((library.getLibraryDirector().getPrenom()));

        return libraryBatchDTO;
    }
}
