package fr.training.spring.Library.exposition.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryAddressDTO {

    @JsonProperty("numero")
    private int numero;

    @JsonProperty("rue")
    private String rue;

    @JsonProperty("codePostal")
    private int codePostal;

    @JsonProperty("ville")
    private String ville;

    public int getNumero() {
        return numero;
    }

    public LibraryAddressDTO(int numero, String rue, int codePostal, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public static final class Builder {
        private int numero;
        private String rue;
        private int codePostal;
        private String ville;

        private Builder() {
        }

        public static Builder aLibraryAddressDTO() {
            return new Builder();
        }

        public Builder numero(int numero) {
            this.numero = numero;
            return this;
        }

        public Builder rue(String rue) {
            this.rue = rue;
            return this;
        }

        public Builder codePostal(int codePostal) {
            this.codePostal = codePostal;
            return this;
        }

        public Builder ville(String ville) {
            this.ville = ville;
            return this;
        }

        public LibraryAddressDTO build() {
            return new LibraryAddressDTO(numero, rue, codePostal, ville);
        }
    }

}
