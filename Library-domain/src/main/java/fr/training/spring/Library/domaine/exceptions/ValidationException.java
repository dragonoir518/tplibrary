package fr.training.spring.Library.domaine.exceptions;

public class ValidationException extends BusinessException {
    public ValidationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
