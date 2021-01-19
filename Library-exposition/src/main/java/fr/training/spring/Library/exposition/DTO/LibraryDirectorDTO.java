package fr.training.spring.Library.exposition.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class LibraryDirectorDTO {
    @JsonProperty("prenom")
    @NotNull
    private String prenom;

    @JsonProperty("nom")
    @NotNull
    private String nom;

    public LibraryDirectorDTO() {
    }

    public LibraryDirectorDTO(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
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
