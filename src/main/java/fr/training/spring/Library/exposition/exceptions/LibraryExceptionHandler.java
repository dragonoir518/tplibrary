package fr.training.spring.Library.exposition.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.Library.domaine.exceptions.LibraryNotFoundExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice  //ce controler peut s'appliquer à plusieurs controller et les différents exceptions
public class LibraryExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(LibraryExceptionHandler.class);

  /*  @ExceptionHandler(value = {LibraryNotFoundExeption.class})  //si on rencontre LibraryNotFoundExeption alors envoyer le message sur l'interface
    public ResponseEntity<Object> handlerLibraryNotFoundException(RuntimeException ex) {
            return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), 404);  //HttpStatus.BAD_REQUEST

    }*/

    @ExceptionHandler(value = {LibraryNotFoundExeption.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String libraryNotFound(RuntimeException ex){
        final String errorCode = "404";
        logger.info("Erreur {} {}",errorCode,ex.getMessage());
        return errorCode+" "+ex.getMessage();

    }
}
