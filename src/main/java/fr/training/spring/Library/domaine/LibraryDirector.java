package fr.training.spring.Library.domaine;


import fr.training.spring.Library.domaine.ddd.DDD;
import fr.training.spring.Library.domaine.exceptions.ErrorCodes;
import fr.training.spring.Library.domaine.exceptions.ValidationException;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;


//@Embeddable  //Value Object sans Id et on Embeddable
@DDD.ValueObject
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

    public void validate() {
        //RG métier un directeur ne peut pas être null dans Library
        if(this.prenom==null || this.nom==null || this.prenom=="" || this.nom=="") {
            throw new ValidationException("Director is null", ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR);
        }
    }

    @Override
    public String toString() {
        return "LibraryDirector{" +
                "prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
