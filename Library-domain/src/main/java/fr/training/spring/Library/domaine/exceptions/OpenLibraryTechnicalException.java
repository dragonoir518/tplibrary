package fr.training.spring.Library.domaine.exceptions;

import org.springframework.web.client.HttpStatusCodeException;

public class OpenLibraryTechnicalException extends TechnicalException {

    private static final long serialVersionUID = 1L;

    public OpenLibraryTechnicalException(final HttpStatusCodeException e) {
        super(e);
    }
}
