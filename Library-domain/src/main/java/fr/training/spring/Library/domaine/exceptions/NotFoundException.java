package fr.training.spring.Library.domaine.exceptions;

public class NotFoundException extends BusinessException{
    public NotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

}
