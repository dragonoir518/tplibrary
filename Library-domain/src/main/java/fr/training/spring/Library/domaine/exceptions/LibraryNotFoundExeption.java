package fr.training.spring.Library.domaine.exceptions;

public class LibraryNotFoundExeption extends RuntimeException {

    public LibraryNotFoundExeption() { super();}

    public LibraryNotFoundExeption(String message) {super(message);}
}
