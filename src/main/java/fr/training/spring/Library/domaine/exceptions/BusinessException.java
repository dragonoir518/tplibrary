package fr.training.spring.Library.domaine.exceptions;

public class BusinessException extends RuntimeException {
    private final String errorCode;


    BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode= errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
