package fr.training.spring.Library.domaine;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable  //Value Object sans Id et on Embeddable
public class LibraryDirector {
    private String prenom;
    private String nom;

    public LibraryDirector(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public LibraryDirector() {

    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
